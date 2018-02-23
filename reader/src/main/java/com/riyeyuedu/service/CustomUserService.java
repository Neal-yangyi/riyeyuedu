package com.riyeyuedu.service;

import com.riyeyuedu.dao.ReaderDao;
import com.riyeyuedu.entity.ReaderEntity;
import com.riyeyuedu.entity.SysRoleEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService implements UserDetailsService {

    private ReaderDao readerDao;

    private SqlSession sqlSession;

    public CustomUserService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String readerName) throws UsernameNotFoundException {
        ReaderEntity reader = readerDao.getReaderByReaderName(sqlSession, readerName);
        //String role = readerDao.getReaderRoleByRid(sqlSession, reader.getRid());
        SysRoleEntity roleEntity = new SysRoleEntity();
        roleEntity.setName("ROLE_READER");
        reader.getRoles().add(roleEntity);
        if (reader == null) {
            System.out.println("用户不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }

        /*List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("reader"));
        return new org.springframework.security.core.userdetails.User(reader.getReaderName(),
                reader.getPassword(), authorities);*/
        System.out.println("readerName: " + readerName);
        System.out.println("userName: " + reader.getUsername() + "password: " + reader.getPassword());
        return reader;
    }
}
