package com.codingmobi.ui.core.bean.systerm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codingmobi.ui.core.utils.mybatis.ConstantsForCore;

public class User implements UserDetails, Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String loginName;

    private String password;

    private String nickName;

    private Integer isVaild;

    private Integer gender;

    private String email;

    private String mobilePhone;

    private String qq;

    private Date createTime;

    private Date updateTime;

    private Integer ext1;

    private Integer ext2;

    private Integer ext3;

    private Integer ext4;

    private Integer ext5;

    private Date ext6;

    private Date ext7;

    private Date ext8;

    private Date ext9;

    private Date ext10;

    private String ext11;

    private String ext12;

    private String ext13;

    private String ext14;

    private String ext15;
    
    private List<Role> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(Integer isVaild) {
        this.isVaild = isVaild;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getExt1() {
        return ext1;
    }

    public void setExt1(Integer ext1) {
        this.ext1 = ext1;
    }

    public Integer getExt2() {
        return ext2;
    }

    public void setExt2(Integer ext2) {
        this.ext2 = ext2;
    }

    public Integer getExt3() {
        return ext3;
    }

    public void setExt3(Integer ext3) {
        this.ext3 = ext3;
    }

    public Integer getExt4() {
        return ext4;
    }

    public void setExt4(Integer ext4) {
        this.ext4 = ext4;
    }

    public Integer getExt5() {
        return ext5;
    }

    public void setExt5(Integer ext5) {
        this.ext5 = ext5;
    }

    public Date getExt6() {
        return ext6;
    }

    public void setExt6(Date ext6) {
        this.ext6 = ext6;
    }

    public Date getExt7() {
        return ext7;
    }

    public void setExt7(Date ext7) {
        this.ext7 = ext7;
    }

    public Date getExt8() {
        return ext8;
    }

    public void setExt8(Date ext8) {
        this.ext8 = ext8;
    }

    public Date getExt9() {
        return ext9;
    }

    public void setExt9(Date ext9) {
        this.ext9 = ext9;
    }

    public Date getExt10() {
        return ext10;
    }

    public void setExt10(Date ext10) {
        this.ext10 = ext10;
    }

    public String getExt11() {
        return ext11;
    }

    public void setExt11(String ext11) {
        this.ext11 = ext11 == null ? null : ext11.trim();
    }

    public String getExt12() {
        return ext12;
    }

    public void setExt12(String ext12) {
        this.ext12 = ext12 == null ? null : ext12.trim();
    }

    public String getExt13() {
        return ext13;
    }

    public void setExt13(String ext13) {
        this.ext13 = ext13 == null ? null : ext13.trim();
    }

    public String getExt14() {
        return ext14;
    }

    public void setExt14(String ext14) {
        this.ext14 = ext14 == null ? null : ext14.trim();
    }

    public String getExt15() {
        return ext15;
    }

    public void setExt15(String ext15) {
        this.ext15 = ext15 == null ? null : ext15.trim();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
//		return this.getUsername();
		return this.loginName;
	}
	
	@Override
    public String getPassword() {
        return password;
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(ConstantsForCore.YES_INT.equals(this.getIsVaild())){
			return true;
		}else{
			return false;
		}
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
}