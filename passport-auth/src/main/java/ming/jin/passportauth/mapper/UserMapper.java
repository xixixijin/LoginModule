package ming.jin.passportauth.mapper;

import ming.jin.passportauth.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Haokun
 * @date 2020/2/26 23:50
 * <p>
 * LoginModule
 */
@Mapper
public interface UserMapper {


    @Insert("insert into user (username,mobile,email,create_time,update_time,password) values(#{username},#{mobile},#{email},now(),now(),#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int addUser(User user);
    @Select("select * from user where mobile=#{mobile} limit 1 ")
    User getUserByMobile(String mobile);
    @Select("select * from user where email=#{email} limit 1")
    User getUserByEmail(String email);
    @Select("select * from user where #{accountType}=#{username}")
    int exist(String accountType,String username);

}
