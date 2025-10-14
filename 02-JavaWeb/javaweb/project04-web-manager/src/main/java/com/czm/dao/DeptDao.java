package com.czm.dao;
import java.util.List;

/**
 * 3、数据访问层，处理数据访问数据
 * 面向接口编程，定义一个接口，然后根据业务多个实现类。
 * 采用此编程方式，如果业务需求变更时，可以保留原先的实现类，去新增实现类就可以了。增强程序的扩展性！
 *
 */

public interface DeptDao  {
    public List<String> list();
}
