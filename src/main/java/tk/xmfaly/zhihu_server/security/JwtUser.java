package tk.xmfaly.zhihu_server.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final int id;
    private final String userName;
    private final String passWord;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
            int id,
            String userName,
            String passWord,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.authorities = authorities;
        this.enabled = true;
        this.lastPasswordResetDate = null;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
