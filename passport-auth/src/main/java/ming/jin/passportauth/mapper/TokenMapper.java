package ming.jin.passportauth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Haokun
 * @date 2020/2/26 16:34
 * <p>
 * LoginModule
 */
@Mapper
public interface TokenMapper {
    @Select("select token from __")
    String getToken(String userId);

    @Insert("insert into __ where ")
    Integer insert(String userId,String token,long expire);

    @Update("update __ set token=#{token} where user_id=#{userId}")
    Integer updateToken(String userId,String token);
}
