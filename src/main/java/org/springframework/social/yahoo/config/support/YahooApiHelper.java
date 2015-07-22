package org.springframework.social.yahoo.config.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.yahoo.api.Yahoo;

public class YahooApiHelper implements ApiHelper<Yahoo> {

    private final UsersConnectionRepository usersConnectionRepository;
    
    private final UserIdSource userIdSource;

    private YahooApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    public Yahoo getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for Twitter");
        }

        Connection<Yahoo> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(Yahoo.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default TwitterTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }
    


    private final static Logger logger = LoggerFactory.getLogger(YahooApiHelper.class);
}


