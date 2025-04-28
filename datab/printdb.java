package datab;

public class printdb {
    String sql = "SELECT * FROM dept";
try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
 while (rs.next()) {
                String deptno = rs.getString(1);
                String dname = rs.getString(2);
                String Loc = rs.getString(3);

                System.out.println(deptno + dname + Loc);

            }

            -- Method 1.
SELECT 1
FROM table_name
WHERE unique_key = value;

-- Method 2.
SELECT COUNT(1)
FROM table_name
WHERE unique_key = value;
}


I would prefer not use Count function at all:

IF [NOT] EXISTS ( SELECT 1 FROM MyTable WHERE ... )
     <do smth>
For example if you want to check if user exists before inserting it into the database the query can look like this:

IF NOT EXISTS ( SELECT 1 FROM Users WHERE FirstName = 'John' AND LastName = 'Smith' )
BEGIN
    INSERT INTO Users (FirstName, LastName) VALUES ('John', 'Smith')
END
