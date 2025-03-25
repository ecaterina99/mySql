use social_network_system;

create index request_status_index
    on friends (request_status);

create fulltext index content_idx
    on status (content);

create index user_full_name_index
    on user (name, surname);

create definer = root@localhost view user_info as
select `social_network_system`.`user`.`name`       AS `name`,
       `social_network_system`.`user`.`surname`    AS `surname`,
       `social_network_system`.`user`.`birth_date` AS `birth_date`,
       `social_network_system`.`user`.`birthplace` AS `birthplace`
from `social_network_system`.`user`;

create
    definer = root@localhost procedure delete_user_by_id(IN id_user int)
BEGIN
    DELETE from user WHERE user_id=id_user;

end;

create
    definer = root@localhost function get_friends_count(id_user int) returns int deterministic
BEGIN
DECLARE friends_count INT;

SELECT COUNT(*) INTO friends_count
FROM friends
WHERE (receiver_id = id_user OR sender_id=id_user) AND request_status = 'accepted';
    RETURN friends_count;
END;

create
    definer = root@localhost procedure insert_user(IN p_name varchar(35), IN p_surname varchar(35),
                                                   IN p_email varchar(35), IN p_birth_date date,
                                                   IN p_birthplace varchar(50), IN p_nickname varchar(35))
BEGIN
   INSERT INTO user (name, surname, email, birth_date, birthplace, nickname)
       VALUES (p_name,p_surname,p_email,p_birth_date,p_birthplace,p_nickname);
END;

create
    definer = root@localhost procedure modify_email(IN p_user_id int, IN p_email varchar(35))
BEGIN
    UPDATE user
    SET email=p_email
    WHERE user_id = p_user_id;
end;


