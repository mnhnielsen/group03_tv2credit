package Creditsystem.Data;

import Creditsystem.Domain.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersistenceHandler implements IPersistenceHandler
{
    private static PersistenceHandler instance;
    private String url;
    private int port;
    private String databaseName;
    private String username;
    private String password;
    private Connection connection = null;

    private int productionID;
    private int roleID;
    private int participantID;


    @Override
    public int getProductionID()
    {
        return productionID;
    }

    @Override
    public int getRoleID()
    {
        return roleID;
    }

    @Override
    public int getParticipantID()
    {
        return participantID;
    }


    private void initializeConfiguration()
    {
        try
        {
            InputStream inputStream = new FileInputStream(getClass().getResource("config.properties").toURI().getPath());
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("db.url");
            port = Integer.valueOf(properties.getProperty("db.port"));
            databaseName = properties.getProperty("db.databaseName");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private PersistenceHandler()
    {
        initializeConfiguration();
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance()
    {
        if (instance == null)
        {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase()
    {
        try
        {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("Connected");
        } catch (SQLException | IllegalArgumentException ex)
        {
            System.out.println("Cannot connect");
        } finally
        {
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public boolean logIn(String username, String password)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
            {
                return false;
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean checkAuthorization(String username)
    {

        boolean isAdmin = false;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            statement.setString(1, username);

            ResultSet sqlReturnValues = statement.executeQuery();
            List<Account> returnValue = new ArrayList<>();
            Account account = null;
            while (sqlReturnValues.next())
            {
                account = new Account(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getBoolean(4));
                returnValue.add(account);
            }
            isAdmin = account.getAdminStatus();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return isAdmin;
    }

    @Override
    public boolean checkUsername(String username)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
            {
                return false;
            }
            if (resultSet.getString(1).equals(username))
            {
                System.out.println("Same username");
                return false;
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean createAdminAccount(Account account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts(username,password,isadmin)VALUES (?,?,?)");
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setBoolean(3, account.getAdminStatus());
            statement.execute();
        } catch (SQLException throwables)
        {
            System.out.println("Could not create new login");
            return false;
        }
        return true;
    }

    @Override
    public boolean createProducerAccount(Producer producerAccount)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO produceraccount(username,password,isadmin, name, email, phoneNumber, company)VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, producerAccount.getUsername());
            statement.setString(2, producerAccount.getPassword());
            statement.setBoolean(3, producerAccount.getAdminStatus());
            statement.setString(4, producerAccount.getName());
            statement.setString(5, producerAccount.getEmail());
            statement.setInt(6, producerAccount.getPhoneNumber());
            statement.setString(7, producerAccount.getCompany());
            statement.execute();
        } catch (SQLException throwables)
        {
            System.out.println("Could not create new login");
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getAccountID(String username)
    {
        int id = 0;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            statement.setString(1, username);

            ResultSet sqlReturnValues = statement.executeQuery();
            List<Account> returnValue = new ArrayList<>();
            Account account = null;
            while (sqlReturnValues.next())
            {
                account = new Account(sqlReturnValues.getInt(1), sqlReturnValues.getString(2));
                returnValue.add(account);
            }
            id = account.getId();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public ArrayList getProductions()
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM production WHERE ispublished = TRUE");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Production> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Production(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Production> getUnreleasedProductions()
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM production WHERE ispublished = FALSE");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Production> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Production(resultSet.getInt(1), resultSet.getString(2)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean releaseProduction(Production production)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE production SET ispublished = TRUE WHERE id = ?");
            statement.setInt(1, production.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Production getProductionTitle(String title)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM production WHERE title = ?");
            stmt.setString(1, title);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next())
            {
                return null;
            }
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getString(4), sqlReturnValues.getInt(5), sqlReturnValues.getBoolean(6));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<CreditJoin> getCredits(String title)
    {
        try
        {
            ArrayList<CreditJoin> list = new ArrayList<>();

            PreparedStatement stmt = connection.prepareStatement("SELECT * From participant join credit c on participant.id = c.participantid join role r on c.roleid = r.id JOIN production p on c.productionid = p.id WHERE p.title = ?");
            stmt.setString(1, title);
            ResultSet sqlReturnValues = stmt.executeQuery();
            while (sqlReturnValues.next())
            {
                list.add(new CreditJoin(sqlReturnValues.getString(2), sqlReturnValues.getString(9), sqlReturnValues.getInt(13)));
            }
            return list;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createProduction(Production production)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO production(title, releaseyear, description, createdby, ispublished) VALUES(?,?,?,?,?)");
            statement.setString(1, production.getTitle());
            statement.setInt(2, production.getReleaseYear());
            statement.setString(3, production.getDescription());
            statement.setInt(4, production.getCreatedby());
            statement.setBoolean(5, false);
            statement.execute();

            PreparedStatement productionStatement = connection.prepareStatement("SELECT id FROM production WHERE title = ?");
            productionStatement.setString(1, production.getTitle());
            ResultSet resultSet = productionStatement.executeQuery();
            List<Production> productionList = new ArrayList<>();
            Production productions = null;
            while (resultSet.next())
            {
                productions = new Production(resultSet.getInt(1));
                productionList.add(productions);
            }

            productionID = productions.getId();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createRole(Role role)
    {
        try
        {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO role(name, productionid) VALUES(?,?)");
            statement.setString(1, role.getName());
            statement.setInt(2, productionID);

            statement.execute();

            PreparedStatement roleStatement = connection.prepareStatement("SELECT id FROM role WHERE name=? AND role.productionid=?");
            roleStatement.setString(1, role.getName());
            roleStatement.setInt(2, productionID);

            ResultSet roleResult = roleStatement.executeQuery();
            List<Role> roleList = new ArrayList<>();
            while (roleResult.next())
            {
                Role roles = new Role(roleResult.getInt(1));
                roleList.add(roles);
                roleID = roles.getId();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean checkRoleName(String roleName)
    {
        try
        {
            int i = 0;
            PreparedStatement roleStatement = connection.prepareStatement("SELECT name FROM role");
            ResultSet resultSet = roleStatement.executeQuery();
            while (resultSet.next())
            {
                i++;
                String string = resultSet.getString(1);
                if (roleName.equals(string))
                {
                    roleID = i;
                    return true;
                } else
                {
                    return false;
                }
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createParticipant(Participant participant)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(name, email, phonenumber) VALUES(?,?,?)");
            statement.setString(1, participant.getName());
            statement.setString(2, participant.getEmail());
            statement.setInt(3, participant.getPhoneNumber());
            statement.execute();

            PreparedStatement participantStatement = connection.prepareStatement("SELECT id FROM participant WHERE name = ?");
            participantStatement.setString(1, participant.getName());
            ResultSet participantResult = participantStatement.executeQuery();
            List<Participant> participantList = new ArrayList<>();
            Participant participants = null;
            while (participantResult.next())
            {
                participants = new Participant(participantResult.getInt(1));
                participantList.add(participants);
            }

            participantID = participants.getId();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createCredit(Credits credits)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO credit(productionid,roleid,participantid) VALUES(?,?,?)");
            statement.setInt(1, productionID);
            statement.setInt(2, roleID);
            statement.setInt(3, participantID);
            statement.execute();

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList getUsers()
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Account> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Account(resultSet.getString(2), resultSet.getBoolean(4)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean deleteUser(String userName)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM accounts WHERE username = ?");
            statement.setString(1, userName);
            statement.execute();


        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Participant> getParticipants()
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Participant> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Participant(resultSet.getString(2)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Role> getRoles()
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM role");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Role> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Role(resultSet.getString(2)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Participant getParticipant(String name)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM participant WHERE name = ?");
            stmt.setString(1, name);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next())
            {
                return null;
            }
            return new Participant(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getInt(4));
        } catch (SQLException throwables)
        {
            return null;
        }
    }

    @Override
    public Participant getParticipantID(String name)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM participant WHERE name = ?");
            statement.setString(1, name);

            ResultSet sqlReturnValues = statement.executeQuery();
            List<Participant> returnValue = new ArrayList<>();
            Participant participant = null;
            while (sqlReturnValues.next())
            {
                participant = new Participant(sqlReturnValues.getInt(1));
                returnValue.add(participant);
            }
            participantID = participant.getId();

            return new Participant(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getInt(4));

        } catch (SQLException throwables)
        {
            return null;
        }
    }

    @Override
    public Role getRoleID(String name)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM role WHERE name = ?");
            statement.setString(1, name);

            ResultSet sqlReturnValues = statement.executeQuery();
            List<Role> returnValue = new ArrayList<>();
            Role role = null;
            while (sqlReturnValues.next())
            {
                role = new Role(sqlReturnValues.getInt(1));
                returnValue.add(role);
            }
            roleID = role.getId();

            return new Role(sqlReturnValues.getInt(1));
        } catch (SQLException throwables)
        {

            return null;
        }
    }

    @Override
    public Producer getProducerAccount(int id)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produceraccount WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next())
            {
                return null;
            }
            return new Producer(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getBoolean(4), sqlReturnValues.getString(5), sqlReturnValues.getString(6), sqlReturnValues.getInt(7), sqlReturnValues.getString(8));
        } catch (SQLException throwables)
        {
            return null;
        }
    }

    @Override
    public Account getAccount(int id)
    {
        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next())
            {
                return null;
            }
            return new Account(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getBoolean(4));
        } catch (SQLException throwables)
        {
            return null;
        }
    }

    @Override
    public ArrayList<Production> getMyProductions(int id)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM production WHERE createdby = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Production> returnValues = new ArrayList<>();
            while (resultSet.next())
            {
                returnValues.add(new Production(resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6)));
            }
            return returnValues;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    /*@Override
    public boolean changeCreditRoleID(Credits credits)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE credit SET roleid = ? WHERE productionid = ? AND participantid = ?");
            statement.setInt(1, credits.getRoleId());
            System.out.println(credits.getRoleId());
            statement.setInt(2,credits.getProductionId());
            System.out.println(credits.getProductionId());
            statement.setInt(3,credits.getParticipantId());
            System.out.println(credits.getParticipantId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeCreditParticipantID(Credits credits)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE credit SET participantid = ? WHERE productionid = ? AND roleid = ?");
            statement.setInt(1, credits.getParticipantId());
            statement.setInt(2,credits.getProductionId());
            statement.setInt(3,credits.getRoleId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

     */

    @Override
    public boolean changeAccountPhone(Producer account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE produceraccount SET phonenumber = ? WHERE id = ?");
            statement.setInt(1, account.getPhoneNumber());
            statement.setInt(2, account.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAccountEmail(Producer account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE produceraccount SET email = ? WHERE id = ?");
            statement.setString(1, account.getEmail());
            statement.setInt(2, account.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAccountName(Producer account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE produceraccount SET name = ? WHERE id = ?");
            statement.setString(1, account.getName());
            statement.setInt(2, account.getId());
            System.out.println(account.getId());
            System.out.println(account.getName());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAccountPassword(Producer account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE produceraccount SET password = ? WHERE id = ?");
            statement.setString(1, account.getPassword());
            statement.setInt(2, account.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAccountUsername(Account account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET username = ? WHERE id = ?");
            statement.setString(1, account.getUsername());
            statement.setInt(2, account.getId());
            System.out.println(account.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAccountPassword(Account account)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET password = ? WHERE id = ?");
            statement.setString(1, account.getPassword());
            statement.setInt(2, account.getId());
            statement.execute();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCredit(String title)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM credit WHERE title = ?");
            statement.setString(1, title);
            statement.execute();


        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}

