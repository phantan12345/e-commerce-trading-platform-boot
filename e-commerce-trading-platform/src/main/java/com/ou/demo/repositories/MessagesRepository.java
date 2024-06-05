/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.repositories;

import com.ou.demo.pojos.Messages;
import com.ou.demo.pojos.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public interface MessagesRepository extends JpaRepository<Messages, Integer> {

    @Query(value = "SELECT "
            + "    CASE "
            + "        WHEN m.sent_to = ?1 THEN m.sent_by "
            + "        ELSE m.sent_to "
            + "    END AS user_id, "
            + "    MAX(m.id) AS latest_message_id, "
            + "    u.name AS name, "
            + "     u.avatar as avatar, "
            + "    (SELECT message FROM messages WHERE id = MAX(m.id) LIMIT 1) AS message, "
            + "     (SELECT status FROM messages WHERE id = MAX(m.id) LIMIT 1) AS status, "
            + "    (SELECT sent_by FROM messages WHERE id = MAX(m.id) LIMIT 1) AS sent_by "
            + "FROM "
            + "    messages m "
            + "JOIN "
            + "    user u ON (m.sent_to = u.id OR m.sent_by = u.id) "
            + "WHERE "
            + "    (m.sent_to = ?1 OR m.sent_by = ?1)"
            + "    AND u.id != ?1 "
            + "GROUP BY "
            + "    CASE "
            + "        WHEN m.sent_to = ?1 THEN m.sent_by"
            + "        ELSE m.sent_to"
            + "    END,"
            + "    u.username "
            + "ORDER BY "
            + "    latest_message_id desc",
            nativeQuery = true)
    List<Object[]> getUserDistinctMessages(@Param("userId") Integer userId );

    @Query("SELECT m  FROM Messages m WHERE (m.sentBy.id = :loggedInUserId AND m.sentTo.id = :chatRecipientId) OR (m.sentBy.id = :chatRecipientId AND m.sentTo.id = :loggedInUserId) ORDER BY m.id DESC")
    List<Messages> getUserMessagesWithUser(@Param("loggedInUserId") int loggedInUserId, @Param("chatRecipientId") int chatRecipientId);

    @Modifying
    @Query("UPDATE Messages m SET status = 'R' WHERE m.sentBy.id = :chatRecipientId AND m.sentTo.id = :loggedInUserId AND status = 'U'")
    void markMessagesReadForUsers(@Param("loggedInUserId") Integer loggedInUserId, @Param("chatRecipientId") Integer chatRecipientId);
}
