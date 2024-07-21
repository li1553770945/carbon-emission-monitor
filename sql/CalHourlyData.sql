CREATE DEFINER=`root`@`%` PROCEDURE `CalHourlyData`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE `current_device_id` INT;
    DECLARE `previous_value` DECIMAL(10, 2);
    DECLARE `current_value` DECIMAL(10, 2);
    DECLARE cur CURSOR FOR SELECT id FROM device;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    SET @current_hour = DATE_FORMAT(NOW(), '%Y-%m-%d %H:00:00');
    SET @previous_hour = DATE_FORMAT(DATE_SUB(@current_hour, INTERVAL 1 HOUR), '%Y-%m-%d %H:00:00');


    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO `current_device_id`;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 获取每个设备的最新数据
        SELECT value INTO `previous_value`
        FROM device_data
        WHERE device_id = `current_device_id`  AND datetime < @previous_hour
        ORDER BY datetime DESC
        LIMIT 1;

        -- 获取每个设备的上一个小时的最新数据
        SELECT value INTO `current_value`
        FROM device_data
        WHERE device_id = `current_device_id`  AND datetime < @current_hour
        ORDER BY datetime DESC
        LIMIT 1;

        IF EXISTS (SELECT * FROM device_hourly_data WHERE device_id = current_device_id AND datetime = @current_hour) THEN
            UPDATE device_hourly_data
            SET value = current_value - previous_value
            WHERE device_id = current_device_id AND datetime = @current_hour;
        ELSE
            INSERT INTO device_hourly_data (device_id, value, datetime)
            VALUES (current_device_id, current_value - previous_value, @current_hour);
        END IF;
    END LOOP;

    CLOSE cur;
END