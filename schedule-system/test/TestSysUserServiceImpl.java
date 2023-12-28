import com.sun.qing.dao.SysUserDao;
import com.sun.qing.dao.impl.SysUserDaoImpl;
import com.sun.qing.pojo.SysUser;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSysUserServiceImpl {
    static SysUserDao sysUserDao;
    @BeforeClass
    public static void initUserDao() {
        sysUserDao = new SysUserDaoImpl();
    }

    @Test
    public void testLogin() {
        String xx = "zhangsan";
        SysUser password = sysUserDao.getPassword(xx);
        System.out.println(password);
    }
}
