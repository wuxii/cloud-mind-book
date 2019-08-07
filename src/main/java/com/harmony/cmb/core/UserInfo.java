package com.harmony.cmb.core;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author wuxii
 */
@Getter
@Builder
public class UserInfo {

    private static final UserInfo ANONYMOUS = new UserInfo("anonymous", 0l);

    public static UserInfo currentUserInfo() {
        // SecurityContext context = SecurityContextHolder.getContext();
        return ANONYMOUS;
    }

    private final String username;
    private final Long userId;

    public static Long currentUserId() {
        return currentUserInfo().getUserId();
    }

    public boolean isAnonymous() {
        return userId == 0;
    }

    private UserInfo(String username, Long userId) {
        Assert.notNull(userId, "userId");
        Assert.isTrue(StringUtils.hasText(username), "username");
        this.username = username;
        this.userId = userId;
    }

    public static class UserInfoBuilder {

        public UserInfoBuilder userId(Long userId) {
            Assert.notNull(userId, "userId");
            if (userId <= 0) {
                throw new IllegalArgumentException("not allow user id < 0. " + userId);
            }
            this.userId = userId;
            return this;
        }

    }

}
