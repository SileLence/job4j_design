package ru.job4j.question;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<User> curr = new HashSet<>(current);
        Map<Integer, User> prevMap = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        for (User setUser : current) {
            User mapUser = prevMap.get(setUser.getId());
            if (setUser.equals(mapUser)) {
                curr.remove(setUser);
                prevMap.remove(setUser.getId());
            } else if (setUser.getId() == mapUser.getId()
                    && !setUser.getName().equals(mapUser.getName())) {
                info.setChanged(info.getChanged() + 1);
                curr.remove(setUser);
                prevMap.remove(setUser.getId());
            }
        }
        info.setDeleted(prevMap.size());
        info.setAdded(curr.size());
        return info;
    }
}
