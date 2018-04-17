package tk.xmfaly.zhihu_server.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userInfoRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
