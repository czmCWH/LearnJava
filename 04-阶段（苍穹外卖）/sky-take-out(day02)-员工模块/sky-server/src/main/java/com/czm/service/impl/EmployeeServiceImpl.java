package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.constant.PasswordConstant;
import com.czm.constant.StatusConstant;
import com.czm.context.BaseContext;
import com.czm.dto.EmployeeDTO;
import com.czm.dto.EmployeeLoginDTO;
import com.czm.dto.EmployeePageQueryDTO;
import com.czm.entity.Employee;
import com.czm.exception.AccountLockedException;
import com.czm.exception.AccountNotFoundException;
import com.czm.exception.PasswordErrorException;
import com.czm.mapper.EmployeeMapper;
import com.czm.result.PageResult;
import com.czm.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

   /* @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private HttpServletRequest request;*/
    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端传过来的明文密码进行加密处理；服务器端存储的密码是密文，而不明文，来提高安全性。
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }


        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }


    @Override
    public void save(EmployeeDTO employeeDTO) {

        log.info("--- EmployeeServiceImpl 线程ID = {}", Thread.currentThread().getId());

        // 1、补充缺失的属性值

        /*
          EmployeeDTO 是一个 DTO 类，只包含前端提交的参数；
          Employee 是一个 Entity 类，包含数据库表中所有的值；
          由于需要存入到 数据库中，所有需要转换为  Employee；
         */
        Employee employee= new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);    // 属性拷贝，将 employeeDTO 中的所有属性值拷贝到 employee 中。基于反射机制

        // 设置默认密码 123456，并用 md5 加密。
        // ⚠️ 用户密码需要加密后才能存入到数据库中，如果直接明文存储到数据库会有泄漏风险。
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置账号初始状态
        employee.setStatus(StatusConstant.ENABLE);

        //设置创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());



        //设置创建人id和修改人id
        //用jwt获取
        //String jwt = request.getHeader(jwtProperties.getAdminTokenName());
        //Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), jwt);
        //TODO


        /* 设置创建人 和 更新人的ID
         * 即需要动态的获取当前登录用户的 ID，后端生成 token 中包含用户ID；另外 获取登录用户ID，会在多个业务中获取到。
         */


        //通过ThreadLocal这个线程的存储空间来储存数据,并使用get(),remove()方法获取和删除数据
        //为了方便使用,直接将ThreadLocal的相关方法和创建其对象的代码封装入BaseContext类中
        // 从 ThreadLocal 获取登录用户ID
        employee.setUpdateUser(BaseContext.getCurrentId());
        employee.setCreateUser(BaseContext.getCurrentId());

        // 2、调用 mapper 中的方法，将员工对象存入到数据库
        employeeMapper.insert(employee);

    }

    /**
     * 分页查询需要使用 PageHelper 插件
     * @param dto
     * @return
     */
    @Override
    public PageResult page(EmployeePageQueryDTO dto) {
        // 1、设置分页参数
        PageHelper.startPage(dto.getPage(), dto.getPageSize());

        // 2、调用mapper查询方法，强转返回类型为 Page
        Page<Employee> page = employeeMapper.list(dto.getName());

        // 3、封装 PageResult对象并返回
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void enableOrDisable(Integer status, Long id) {
        // 1、补充普通参数（更新时间、更新人）
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        // 2、更新员工信息
        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        // ⚠️：把员工密码进行脱敏处理
        employee.setPassword("********");
        return employee;
    }

    @Override
    public void update(EmployeeDTO dto) {
        // 1、补充普通参数
        Employee employee = new Employee();
        // 拷贝属性值
        BeanUtils.copyProperties(dto, employee);
        // 更新时间、更新人
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        // 2、更新员工信息
        employeeMapper.update(employee);
    }
}
