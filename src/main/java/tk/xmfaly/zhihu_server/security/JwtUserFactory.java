package tk.xmfaly.zhihu_server.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tk.xmfaly.zhihu_server.entity.Authority;
import tk.xmfaly.zhihu_server.entity.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserInfo userInfo) {
        return new JwtUser(
                userInfo.getId(),
                userInfo.getUserName(),
                userInfo.getPassWord(),
                mapToGrantedAuthorities(userInfo.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}