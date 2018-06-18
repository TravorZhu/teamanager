package top.travorzhu.teamanager.Entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class MyUserDetailService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetail user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
