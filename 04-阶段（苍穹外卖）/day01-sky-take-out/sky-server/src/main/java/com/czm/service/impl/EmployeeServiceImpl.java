package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.constant.PasswordConstant;
import com.czm.constant.StatusConstant;
import com.czm.context.BaseContext;
import com.czm.dto.EmployeeDTO;
import com.czm.dto.EmployeeLoginDTO;
import com.czm.entity.Employee;
import com.czm.exception.AccountLockedException;
import com.czm.exception.AccountNotFoundException;
import com.czm.exception.PasswordErrorException;
import com.czm.mapper.EmployeeMapper;
import com.czm.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

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

        Employee employee= new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

        //设置默认密码123456,并用md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置账号状态
        employee.setStatus(StatusConstant.ENABLE);

        //设置创建人id和修改人id
        //用jwt获取
        //String jwt = request.getHeader(jwtProperties.getAdminTokenName());
        //Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), jwt);
        //TODO




        //通过ThreadLocal这个线程的存储空间来储存数据,并使用get(),remove()方法获取和删除数据
        //为了方便使用,直接将ThreadLocal的相关方法和创建其对象的代码封装入BaseContext类中
        employee.setUpdateUser(BaseContext.getCurrentId());
        employee.setCreateUser(BaseContext.getCurrentId());


        employeeMapper.insert(employee);

    }
}
