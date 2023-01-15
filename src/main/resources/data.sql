INSERT INTO order_state (state_id,state)
SELECT * FROM (SELECT 1,'ORDERED') AS tmp
WHERE NOT EXISTS (
    SELECT state_id FROM order_state WHERE state_id = 1
) LIMIT 1;

INSERT INTO order_state (state_id,state)
SELECT * FROM (SELECT 2,'OUT_FOR_DELIVERY') AS tmp
WHERE NOT EXISTS (
    SELECT state_id FROM order_state WHERE state_id = 2
) LIMIT 1;

INSERT INTO order_state (state_id,state)
SELECT * FROM (SELECT 3,'DELIVERED') AS tmp
WHERE NOT EXISTS (
    SELECT state_id FROM order_state WHERE state_id = 3
) LIMIT 1;