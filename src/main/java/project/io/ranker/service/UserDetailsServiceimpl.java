package project.io.ranker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.io.ranker.Repositories.ItemRepositories;
import project.io.ranker.Repositories.UserModelRepo;
import project.io.ranker.models.ItemModel;
import project.io.ranker.models.UserDetailsImpl;
import project.io.ranker.models.UserModel;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private final UserModelRepo userModelRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel=userModelRepo.findByUsername(username)
                .orElse(p-> new UsernameNotFoundException( ));
        return UserDetailsImpl.build(userModel);
    }

}
