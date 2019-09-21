USE `acme-explorer`;

DROP FUNCTION IF EXISTS quartile;

DELIMITER // 
CREATE FUNCTION quartile (calc INTEGER) RETURNS VARCHAR(255)
BEGIN
	DECLARE result VARCHAR(255) DEFAULT "";

	SET calc = ROUND(calc, 0);

	CASE
		WHEN (calc <= 25) THEN SET result = "Q1";
		WHEN (calc <= 50) THEN SET result = "Q2";
		WHEN (calc <= 75) THEN SET result = "Q3";
		ELSE SET result = "Q4";
	END CASE;

	RETURN result;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS `getTripsAcceptedByExplorer`;

DELIMITER // 
CREATE PROCEDURE `Acme-Explorer`.`getTripsAcceptedByExplorer` ()
BEGIN
	# Declaramos las variables que vamos a necesitar
	DECLARE totalAcceptedTrip INT DEFAULT 0;

	SELECT  count(t.id) INTO totalAcceptedTrip
		FROM MANAGER m	
		INNER JOIN TRIP t ON (m.id = t.manager_id)	
		INNER JOIN APPLICATION a ON (t.id = a.trip_id)
		WHERE a.status LIKE "ACCEPTED";

	# Ejecutamos la query 
	SELECT  m.name AS 'Manager', count(t.id) AS 'Travels Accepted', 
			quartile((count(t.id)/totalAcceptedTrip) * 100) AS 'Quartile'
		FROM MANAGER m	
		INNER JOIN TRIP t ON (m.id = t.manager_id)	
		INNER JOIN APPLICATION a ON (t.id = a.trip_id)
		WHERE a.status LIKE "ACCEPTED"
		GROUP BY m.name;

END //
DELIMITER ;

CALL `getTripsAcceptedByExplorer`();