DO
'
DECLARE
BEGIN
    IF NOT EXISTS (SELECT 1 FROM task_statuses WHERE id = 1) THEN
        INSERT INTO task_statuses (id, name) VALUES (1, ''Todo'');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM task_statuses WHERE id = 2) THEN
        INSERT INTO task_statuses (id, name) VALUES (2, ''InProgress'');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM task_statuses WHERE id = 3) THEN
        INSERT INTO task_statuses (id, name) VALUES (3, ''Completed'');
    END IF;
     IF NOT EXISTS (SELECT 1 FROM task_statuses WHERE id = 4) THEN
            INSERT INTO task_statuses (id, name) VALUES (4, ''Postponed'');
        END IF;
END;
' LANGUAGE PLPGSQL;