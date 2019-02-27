package com.crhms.seabow.service;

import com.crhms.seabow.model.Role;
import com.crhms.seabow.model.User;

import java.util.Map;

public interface ILoginService {
    User addUser(Map<String, Object> map);

    Role addRole(Map<String, Object> map);

    User findByName(String name);
}
