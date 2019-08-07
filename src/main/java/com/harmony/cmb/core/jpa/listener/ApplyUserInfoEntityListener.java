package com.harmony.cmb.core.jpa.listener;

import com.harmony.cmb.core.UserInfo;
import com.harmony.cmb.core.domain.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author wuxii
 */
@Slf4j
public class ApplyUserInfoEntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        log.debug("before persist apply current user info: {}", entity);
        if (entity instanceof BaseEntity) {
            UserInfo user = UserInfo.currentUserInfo();
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setCreatedAt(Instant.now());
            baseEntity.setCreatorName(user.getUsername());
            baseEntity.setCreatorId(user.getUserId());
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        log.debug("before update apply current user info: {}", entity);
        if (entity instanceof BaseEntity) {
            UserInfo user = UserInfo.currentUserInfo();
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setModifiedAt(Instant.now());
            baseEntity.setModifierName(user.getUsername());
            baseEntity.setModifierId(user.getUserId());
        }
    }

    public static class ApplyUserInfoInerceptor extends EmptyInterceptor {

        private final ApplyUserInfoEntityListener listener;

        public ApplyUserInfoInerceptor(ApplyUserInfoEntityListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
            if (id == null) {
                listener.prePersist(entity);
            } else {
                listener.preUpdate(entity);
            }
            return true;
        }

    }

}
