//JAVA FILE DATABASE PHASE 3

import java.sql.*;
import java.util.Scanner;

public class phase3bperez35 {

    Scanner uX = new Scanner(System.in);

    private Connection c = null;
    private String dbname;
    private boolean isConnected = false;
    
    private void openConnection(String _dbName) {
        dbname = _dbName;

        if (false == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Open database: " + _dbName);

            try {
                String connStr = new String("jdbc:sqlite:");
                connStr = connStr + _dbName;

                // STEP: Register JDBC driver
                Class.forName("org.sqlite.JDBC");

                // STEP: Open a connection
                c = DriverManager.getConnection(connStr);

                // STEP: Diable auto transactions
                c.setAutoCommit(false);

                isConnected = true;
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }

    private void closeConnection() {
        if (true == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Close database: " + dbname);

            try {
                // STEP: Close connection
                c.close();

                isConnected = false;
                dbname = "";
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }

    private String userAcc(String usernameU) {

        //Scanner uA = new Scanner(System.in);
        try {
            
            boolean Umatch, pWmatch, proceedU;
            Umatch = false;     pWmatch = false;     proceedU = false;
        
            System.out.println("WELCOME!!!");
            System.out.println("PLEASE SELECT FOLLOWING:");
            String userArrayMenu[] = {"1: LOG IN","2: CREATE ACCOUNT"};
            System.out.println("--------------------USER ACCOUNT--------------------");
            for (int u = 0; u < userArrayMenu.length; u++) {System.out.print(userArrayMenu[u] + '\t');}
            System.out.print('\n');

            int enter = uX.nextInt();
            
                if(enter == 1) {
                    String strU = "SELECT * FROM userAcc";
                    PreparedStatement p1 = c.prepareStatement(strU);
                    ResultSet rs = p1.executeQuery();

                    System.out.println("Please enter USERNAME");
                    String userNTemp = uX.next();
                    System.out.println("Username is: " + userNTemp);
                    System.out.println("Please enter PASSWORD");
                    String userPTemp = uX.next();
                    System.out.println("Password is: " + userPTemp);

                    String uName, uDebt, uPass;             int uNPch, uAcct, uDMon;
                    uName = "A";    uDebt = "B";    uPass = "C";    uNPch = 0;  uAcct = 0;  uDMon = 0;

                    while(rs.next()) {
                        uName = rs.getString(1);         uNPch = rs.getInt(2);       uAcct = rs.getInt(3);        
                        uDebt = rs.getString(4);         uDMon = rs.getInt(5);       uPass = rs.getString(6);

                        int ctN, ctP;       ctN = 0;    ctP = 0;
                        for (int i = 0; i < uName.length(); i++) {
                            if (uName.length() < userNTemp.length() || uName.length() > userNTemp.length()) {break;}
                            else if (uName.length() == userNTemp.length()) {
                                if(uName.charAt(i) == userNTemp.charAt(i)) {ctN++;}
                            }
                        }

                        for (int j = 0; j < uPass.length(); j++) {
                            if (uPass.length() < userPTemp.length() || uPass.length() > userPTemp.length()) {break;}
                            else if (uPass.length() == userPTemp.length()) {
                                if(uPass.charAt(j) == userPTemp.charAt(j)) {ctP++;}
                            }
                        }

                        if (ctN == uName.length()) {Umatch = true;}
                        if (ctP == uPass.length()) {pWmatch = true;}

                        if (Umatch == true && pWmatch == true) {proceedU = false;       usernameU = userNTemp;      break;}

                        else if (Umatch == true && pWmatch == false) {
                            System.out.print("YOUR PASSWORD IS: ");
                            System.out.println(uPass);
                            usernameU = userNTemp;      proceedU = false;       break;
                        }
                        else if (Umatch == false && pWmatch == true) {
                            System.out.print("YOUR USERNAME IS: ");
                            System.out.println(uName);
                            usernameU = userNTemp;      proceedU = false;       break;
                        }
                    }
                    rs.close();
                    p1.close();

                    if (Umatch == false && pWmatch == false) {
                        System.out.println("NO ACCOUNT FOUND!!! ERROR!!!");
                        System.out.println("DO YOU WISH TO CREATE AN ACCOUNT???");
                        System.out.println("1:YES       2:NO");
                        int sel = uX.nextInt();
                        if(sel == 1) {proceedU = true;}
                        else if (sel == 2) {proceedU = false; usernameU = "NULL";}
                    }
                }
                if (enter == 2 || proceedU == true) {usernameU = createAcct();}

                if (enter == 3) {
                    String susW = "1875";
                    int ctMatch = 0;
                    System.out.println("OK HACKER!!! WHAT'S THE PASSWORD!!!");
                    String inP = uX.next();

                    for (int a = 0; a < susW.length(); a++) {
                        if (susW.length() == inP.length()) {
                            if (susW.charAt(a) == inP.charAt(a)) {ctMatch++;}
                        }
                        else {break;}
                    }
                    if (susW.length() == ctMatch) {
                        System.out.println("Pick your USERNAME login LOL!!!");
                        String p3 = "SELECT * FROM userAcc";
                        PreparedStatement secretSus = c.prepareStatement(p3);
                        ResultSet rs2 = secretSus.executeQuery();

                        String u1, u2, u3, u4, u5, u6;  u1 = "A";   u2 = "B";   u3 = "C";   u4 = "D";   u5 = "E";   u6 = "F";

                        System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", "u_username", "u_numpurchase", "u_acctbal", "u_debt", 
                        "u_debtmon", "u_password");
                        System.out.println("--------------------------------------------------------------------------------------------");

                        while(rs2.next()) {
                            u1 = rs2.getString(1);       u2 = rs2.getString(2);        u3 = rs2.getString(3);
                            u4 = rs2.getString(4);       u5 = rs2.getString(5);        u6 = rs2.getString(6);

                            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", u1, u2, u3, u4, u5, u6);
                        }
                        rs2.close();
                        secretSus.close();                    

                        String pickUser = uX.next();
                        int matchN = 0;
                        String p4 = "SELECT u_username FROM userAcc";
                        PreparedStatement ps4 = c.prepareStatement(p4);
                        ResultSet rs2b = ps4.executeQuery();

                        while(rs2b.next()) {
                            String matchee = rs2b.getString(1);
                            for (int n = 0; n < matchee.length(); n++) {
                                if (matchee.length() == pickUser.length()) {
                                    if (matchee.charAt(n) == pickUser.charAt(n)) {matchN++;}
                                }
                                else {break;}
                            }
                        }
                        rs2b.close();
                        ps4.close();

                        if (pickUser.length() == matchN) {
                            System.out.println("NAME MATCHES FROM LIST!!!");
                            usernameU = pickUser;
                        }
                        else {
                            System.out.println("Name has been set to NULL. PLEASE EXIT PROGRAM ASAP!!");
                            usernameU = "NULL";
                        }
                    }
                    else if (susW.length() != ctMatch) {System.out.println("NICE TRY MAC!!!");  usernameU = "NULL";}
                }
            
            System.out.println("Success userAcct");
        } 
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
        //uA.close();
        return usernameU;
    }


    private String createAcct() {

        //Scanner uA2 = new Scanner(System.in);
        System.out.println("----------------------------CREATE ACCOUNT--------------------------------");
        String _userN, _userP;      int _userMoney; 
        _userN = "A";
        try {
            System.out.println("Create Username:");
            _userN = uX.next();
            System.out.println("Create Password:");
            _userP = uX.next();
            System.out.println("Input Acct. Balance:");
            _userMoney = uX.nextInt();

            String strU2 = "INSERT INTO userAcc VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = c.prepareStatement(strU2);

            stmt.setString(1, _userN);
            stmt.setInt(2, 0);
            stmt.setInt(3, _userMoney);
            stmt.setString(4, "no");
            stmt.setInt(5, 0);
            stmt.setString(6, _userP);
            stmt.addBatch();
            stmt.executeBatch();
            c.commit();

            stmt.close();
            System.out.println("Success createAcct");
            System.out.println("YOU ARE LOGGED ON!!!");
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
        //uX.close();
        return _userN;
    }


    private void movieDB (String userNameM) {

        //Scanner scM = new Scanner(System.in);
        System.out.println("------------------------------MOVIE MENU--------------------------------");
        String movieDBmenu[] = {"SEARCH FILM (1)", "UPLOAD LOST FILM (2)"};

        for (int k = 0; k < movieDBmenu.length; k++) {System.out.print(movieDBmenu[k] + '\t');}
        System.out.print('\n');

        try {
            int op1 = 0;
            op1 = uX.nextInt();

                if (op1 == 2) {
                    String viewBorder = "---------------------------------------------------------------------MAIN LIST-------------" +
                    "-------------------------------------------------------";
                    System.out.println(viewBorder);
                    String movieTuple[] = new String[18];
                    for (int a = 0; a < movieTuple.length; a++) {movieTuple[a] = "NULL";}

                    String tupleCol[] = {
                        "title:", "year:", "genre:", "score:", "producer:", "studios:", "country:", 
                        "description:", "actors:", "type:", "rating:", "nominations:", "language:", 
                        "daterel:", "length:", "strkav:", "color:", "preav:"
                    };

                    for (int i = 0; i < movieTuple.length; i++) {
                        System.out.println("ENTER " + tupleCol[i]);
                        String inputSus = uX.next();
                        movieTuple[i] = inputSus;
                    }

                    String sqlU = 
                    "INSERT INTO movies VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = c.prepareStatement(sqlU);

                    for (int j = 0; j < movieTuple.length; j++) {
                        if (j == 1) {
                            int ksus = Integer.parseInt(movieTuple[j]); 
                            stmt.setInt((j + 1), ksus);
                        }
                        else if (j == 3) {
                            float fsus = Float.parseFloat(movieTuple[j]);
                            stmt.setFloat((j + 1), fsus);
                        }
                        else { 
                            stmt.setString((j + 1), movieTuple[j]);
                        }
                    }
                    stmt.addBatch();
                    stmt.executeBatch();
                    c.commit();
                    stmt.close();
                }
                else if (op1 == 1) {
                    String movieSearchBorder = "----------------------------------------------------------MOVIE SEARCH-----" + 
                    "----------------------------------------------------";
                    System.out.println(movieSearchBorder);
                    String searchOps[] = {"TITLE (1)", "GENRE (2)", "YEAR (3)", "PRODUCER (4)", "ACTOR/ACTRESS (5)", "POPULARITY (6)"};
                    for (int k = 0; k < searchOps.length; k++) {System.out.print(searchOps[k] + '\t');}
                    System.out.print('\n');

                    String sql = "A";
                    int op2 = uX.nextInt();
                    if (op2 == 1) {
                        System.out.println("Input Title:");         String _title = uX.next();
                        sql = "SELECT * FROM movies WHERE m_title = '" + _title + "'";
                    }
                    else if (op2 == 2) {
                        System.out.println("Input Genre:");         String _genre = uX.next();
                        sql = "SELECT * FROM movies WHERE m_genre like '%" + _genre + "%'";
                    }
                    else if (op2 == 3) {
                        System.out.println("Input Year:");          int _year = uX.nextInt();
                        sql = "SELECT * FROM movies WHERE m_year =" + _year;
                    }
                    else if (op2 == 4) {
                        System.out.println("Input Producer:");      String _prod = uX.next();
                        sql = "SELECT * FROM movies WHERE m_producer like '%" + _prod + "%'";
                    }
                    else if (op2 == 5) {
                        System.out.println("Input Actor/Actress:"); String _actor = uX.next();
                        sql = "SELECT * FROM movies WHERE m_actors like '%" + _actor + "%'";
                    }
                    else if (op2 == 6) {
                        String hotlistBorder = "------------------------------------------------------HOTLIST MOVIES-----------" +
                        "-----------------------------------------";
                        System.out.println(hotlistBorder);
                        sql = "SELECT * FROM hotlist";
                        PreparedStatement stmtM6 = c.prepareStatement(sql);
                        ResultSet rsM6 = stmtM6.executeQuery();

                        System.out.printf("%s\t%s\t%s\t%s\n", "title", "year", "genre", "studios");
                        System.out.println("-----------------------------------------------------------------------");

                        while(rsM6.next()) {
                            String m61, m62, m63, m64;

                            m61 = rsM6.getString(1);    m62 = rsM6.getString(2);    m63 = rsM6.getString(3);
                            m64 = rsM6.getString(4);

                            System.out.printf("%s\t%s\t%s\t%s\n", m61, m62, m63, m64);
                        }
                        rsM6.close();       stmtM6.close();
                    }
                    
                    if (op2 >= 1 && op2 <= 5) {
                        System.out.println("Print Result:");
                        System.out.println("-------------------------------------------------------------------------------------------------");
                        PreparedStatement stmtM = c.prepareStatement(sql);
                        ResultSet rsM = stmtM.executeQuery();

                        System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", 
                        "title", "year", "genre", "score", "producer", "studios", "country", "description", "actors", "type", "rating", 
                        "nominations", "language", "daterel", "length", "strkav", "color", "preav");
                        System.out.println("----------------------------------------------------------------------------------------------------------");

                        while(rsM.next()) {
                            String m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18;

                            m1 = rsM.getString(1);  m2 = rsM.getString(2);  m3 = rsM.getString(3);  m4 = rsM.getString(4);
                            m5 = rsM.getString(5);  m6 = rsM.getString(6);  m7 = rsM.getString(7);  m8 = rsM.getString(8);
                            m9 = rsM.getString(9);  m10 = rsM.getString(10);  m11 = rsM.getString(11);  m12 = rsM.getString(12);
                            m13 = rsM.getString(13);  m14 = rsM.getString(14);  m15 = rsM.getString(15);  m16 = rsM.getString(16);
                            m17 = rsM.getString(17);  m18 = rsM.getString(18);

                            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", 
                            m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18);
                        }
                        rsM.close();
                        stmtM.close();
                    }
                }
                System.out.println("Success moviesDB");
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
        //scM.close();
    }

    //SEE IF YOU WANT TO DO EITHER "Buy/Rent" OR "Credit/Debit"
    private void purchaseList(String userNameP) {

        //Scanner pL = new Scanner(System.in);
        System.out.println("--------------------------------------PURCHASES MENU-------------------------------------");
        String pLMenu[] = {"MAKE PURCHASE (1)", "SEE PURCHASE LIST (2)"};
        for(int p = 0; p < pLMenu.length; p++){System.out.print(pLMenu[p] + '\t');}
        System.out.print('\n');
        
        try {
            int selectP = uX.nextInt();

            if (selectP == 1) {
                System.out.println("---------------------MAKE PURCHASE--------------------");
                System.out.print("Select title: ");
                String ptitle = uX.next();
                System.out.print("Payment Method: ");
                String ppayM = uX.next();

                String sql = "SELECT * FROM movies WHERE m_title = '" + ptitle + "'";
                PreparedStatement pSt = c.prepareStatement(sql);
                ResultSet rs = pSt.executeQuery();

                String _title, _genre;      int _year;      float _score;
                _title = "A";   _genre = "B";   _score = 0;     _year = 0;

                while(rs.next()) {
                    _title = rs.getString(1);   _year = rs.getInt(2);
                    _genre = rs.getString(3);   _score = rs.getFloat(4);
                }
                rs.close();     pSt.close();

                String sql1b = "SELECT u_acctbal FROM userACC WHERE u_username = '" + userNameP + "'";
                PreparedStatement pSt1b = c.prepareStatement(sql1b);
                ResultSet rs1b = pSt1b.executeQuery();
                int currBal = 0;
                while(rs1b.next()) {currBal = rs1b.getInt(1);}  rs1b.close();   pSt1b.close();             
                
                String sql2 = "SELECT * FROM purchases WHERE p_username = '" + userNameP + "'";
                PreparedStatement pSt2 = c.prepareStatement(sql2);
                ResultSet rs2 = pSt2.executeQuery();

                String internalUserN = "A";     boolean searchMatch1, searchMatch2;     int numP = 0;
                searchMatch1 = false;           searchMatch2 = false;                   String titlematch = "A";
                int ctP1 = 0;       int ctP2 = 0;

                while(rs2.next()) {
                    internalUserN = rs2.getString(1);   numP = rs2.getInt(2);   titlematch = rs2.getString(4);

                    ctP1 = 0;   ctP2 = 0;
                    for (int p1 = 0; p1 < internalUserN.length(); p1++) {
                        if(internalUserN.length() == userNameP.length()) {
                            if(internalUserN.charAt(p1) == userNameP.charAt(p1)) {ctP1++;}
                        }
                        else {break;}
                    }

                    for (int p2 = 0; p2 < titlematch.length(); p2++) {
                        if(titlematch.length() == _title.length()) {
                            if(titlematch.charAt(p2) == _title.charAt(p2)) {ctP2++;}
                        }
                        else {break;}
                    }
                    if (internalUserN.length() == ctP1) {searchMatch1 = true;}
                    else {searchMatch1 = false;}

                    if (titlematch.length() == ctP2) {searchMatch2 = true;}
                    else {searchMatch2 = false;}

                    if (searchMatch1 == true && searchMatch2 == true) {break;}

                }
                rs2.close();     pSt2.close();

                //System.out.println("# of characters in film input: " + ctP2);
                //System.out.println("# of characters in film found: " + titlematch.length());

                if (searchMatch2 == false) {
                    String sql3 = "INSERT INTO purchases VALUES (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pSt3 = c.prepareStatement(sql3);

                    if(searchMatch1 == true) {numP += 1;        currBal -= 10;}
                    else if (searchMatch1 == false) {numP = 1;      currBal -= 10;}

                    pSt3.setString(1, userNameP);       pSt3.setInt(2, numP);       pSt3.setInt(3, currBal);          
                    pSt3.setString(4, _title);          pSt3.setInt(5, _year);      pSt3.setString(6, _genre);  
                    pSt3.setFloat(7, _score);           pSt3.setInt(8, 0);          pSt3.setString(9, "yes");   
                    pSt3.setString(10, ppayM);
                    pSt3.addBatch();            
                    pSt3.executeBatch();        c.commit();         pSt3.close();

                    extraP1(userNameP, numP);
                    extraP2(userNameP, currBal);


                }
                else if (searchMatch2 == true) {
                    System.out.println("FILM " + _title + " HAS ALREADY BEEN PURCHASED BY " + userNameP + "!!!");
                }

            }
            else if (selectP == 2) {
                String pBorder = "---------------------------------------------------------PURCHASE LIST-------------------" +
                "---------------------------------------------";
                System.out.println(pBorder);
                String sql2 = "SELECT * FROM purchases WHERE p_username = '" + userNameP + "'";
                PreparedStatement stmtP2 = c.prepareStatement(sql2);
                ResultSet rsP2 = stmtP2.executeQuery();

                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "userName", "# of purchases", "acctbal", "title", "year",
                "genre", "score", "cost", "paid", "purchasetype");
                System.out.println("--------------------------------------------------------------------------------------------");

                String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;

                while(rsP2.next()) {
                    p1 = rsP2.getString(1);     p2 = rsP2.getString(2);     p3 = rsP2.getString(3);     p4 = rsP2.getString(4);
                    p5 = rsP2.getString(5);     p6 = rsP2.getString(6);     p7 = rsP2.getString(7);     p8 = rsP2.getString(8);
                    p9 = rsP2.getString(9);     p10 = rsP2.getString(10); 

                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);
                }
                rsP2.close();   stmtP2.close();
            }

            System.out.println("Success purchase");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
        //pL.close();
    }

    private void extraP1 (String user, int numVal) {

        try {
            Statement st4 = c.createStatement();
            String sql4 = "UPDATE userAcc SET u_numpurchase = " + numVal + " WHERE u_username = '" + user + "'";
            st4.execute(sql4);      c.commit();         st4.close();

            Statement st5 = c.createStatement();
            String sql5 = "UPDATE purchases SET p_numpurchase = " + numVal + " WHERE p_username = '" + user + "'";
            st5.execute(sql5);      c.commit();         st5.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
        
    }

    private void extraP2 (String user, int balance) {

        try {
            Statement st6 = c.createStatement();
            String sql6 = "UPDATE userAcc SET u_acctbal = " + balance + " WHERE u_username = '" + user + "'";
            st6.execute(sql6);      c.commit();         st6.close();

            Statement st7 = c.createStatement();
            String sql7 = "UPDATE purchases SET p_acctbal = " + balance + " WHERE p_username = '" + user + "'";
            st7.execute(sql7);      c.commit();         st7.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
        }
    }
    
    private void watchList(String usernameX) {

        //Scanner xL = new Scanner(System.in);
        System.out.println("------------------------------------------------WATCHED MENU-----------------------------------------");
        String watchLMenu[] = {"VIEW WATCHLIST (1)", "ADD (2)", "DELETE (3)"};
        for (int x = 0; x < watchLMenu.length; x++) {System.out.print(watchLMenu[x] + '\t');}
        System.out.print('\n');
        int selectX = 0;
        try {
            boolean addFilm = false;
            selectX = uX.nextInt();

            if (selectX == 1) {
                String xBorder1 = "--------------------------------------------------------------------WATCHLIST--------------------" +
                "-------------------------------------------------";
                System.out.println(xBorder1);
                String sql = "SELECT * FROM watched WHERE x_username = '" + usernameX + "'";
                PreparedStatement stmtX1 = c.prepareStatement(sql);
                ResultSet rs = stmtX1.executeQuery();

                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", 
                "username", "title", "year", "genre", "score", "producer", "yourscore", 
                "studios", "description", "actors", "type", "rating", "format");
                System.out.println("----------------------------------------------------------------------------------------------------------");

                while(rs.next()) {
                    String x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13;

                    x1 = rs.getString(1);   x2 = rs.getString(2);   x3 = rs.getString(3);   x4 = rs.getString(4);
                    x5 = rs.getString(5);   x6 = rs.getString(6);   x7 = rs.getString(7);   x8 = rs.getString(8);
                    x9 = rs.getString(9);   x10 = rs.getString(10); x11 = rs.getString(11); x12 = rs.getString(12);
                    x13 = rs.getString(13);

                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", 
                    x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13);
                }
                rs.close();     stmtX1.close();
            
            }
            else if (selectX == 2) {
                System.out.println("---------------------------------ADD FILM TO WATCHLIST--------------------------------");
                System.out.println("ADD FILM:");
                String film = uX.next();
                String sql = "SELECT x_title FROM watched WHERE x_username = '" + usernameX + "'";
                PreparedStatement stmtA = c.prepareStatement(sql);
                ResultSet rs = stmtA.executeQuery();

                int ctF = 0;
                while(rs.next()) {
                    ctF = 0;
                    String matchF = rs.getString(1);
                    for (int x = 0; x < matchF.length(); x++) {
                        if (matchF.length() == film.length()) {
                            if (matchF.charAt(x) == film.charAt(x)) {ctF++;}
                        }
                        else {break;}
                    }

                    if (matchF.length() == ctF) {addFilm = false; break;}
                    else {addFilm = true;}
                }
                rs.close();     stmtA.close();

                if (addFilm == true) {
                    String _title, _genre, _producer, _studios, _desc, _actors, _type, _rating, _format;
                    int _year = 0;      float _score, _yourscore;

                    _title = "A";   _genre = "A";   _producer = "A";   _studios = "A";  _desc = "A";    
                    _actors = "A";  _type = "A";    _rating = "A";     _score = 0;   _yourscore = 0;   
                    _format = "A";

                    System.out.println("What format is this film in?? (i.e., Digital, Blu-Ray, VHS, etc...");
                    _format = uX.next();

                    String sqlX = "SELECT * FROM movies WHERE m_title = '" + film + "'";
                    PreparedStatement stmtB = c.prepareStatement(sqlX);
                    ResultSet rsB = stmtB.executeQuery();

                    while(rsB.next()) {
                        _title = rsB.getString(1);      _year = rsB.getInt(2);          _genre = rsB.getString(3);
                        _score = rsB.getFloat(4);       _producer = rsB.getString(5);   _studios = rsB.getString(6);
                        _desc = rsB.getString(8);       _actors = rsB.getString(9);     _type = rsB.getString(10);
                        _rating = rsB.getString(11);
                    }
                    rsB.close();    stmtB.close();

                    String sqlY = "SELECT * FROM score WHERE s_title = '" + film + "'";
                    PreparedStatement stmtC = c.prepareStatement(sqlY);
                    ResultSet rsC = stmtC.executeQuery();

                    while(rsC.next()) {_yourscore = rsC.getInt(7);}
                    rsC.close();    stmtC.close();

                    String sqlZ = "INSERT INTO watched VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmtD = c.prepareStatement(sqlZ);
                    
                    stmtD.setString(1, usernameX);      stmtD.setString(2, _title);     stmtD.setInt(3, _year);
                    stmtD.setString(4, _genre);         stmtD.setFloat(5, _score);      stmtD.setString(6, _producer);
                    stmtD.setFloat(7, _yourscore);      stmtD.setString(8, _studios);   stmtD.setString(9, _desc);
                    stmtD.setString(10, _actors);       stmtD.setString(11, _type);     stmtD.setString(12, _rating);
                    stmtD.setString(13, _format);       stmtD.addBatch();
                    stmtD.executeBatch();       c.commit();         stmtD.close();

                    
                    int countM = 0;     boolean addWFilm = false;
                    String sqlRo = "SELECT w_title FROM wishlist WHERE w_username = ?";
                    PreparedStatement stmtRo = c.prepareStatement(sqlRo);
                    stmtRo.setString(1, usernameX);
                    ResultSet rsRo = stmtRo.executeQuery();

                    while(rsRo.next()) {
                        String matchF2 = rsRo.getString(1);
                        countM = 0;
                        for (int x = 0; x < matchF2.length(); x++) {
                            if (matchF2.length() == film.length()) {
                                if (matchF2.charAt(x) == film.charAt(x)) {countM++;}
                            }
                            else {break;}
                        }
                        if (matchF2.length() == countM) {addWFilm = true; break;}
                        else {addWFilm = false;}
                    }
                    rsRo.close();     stmtRo.close();
                    if (addWFilm == true) {
                        String sqlBeta = "DELETE FROM wishlist WHERE w_title = ? AND w_username = ?";
                        PreparedStatement xer = c.prepareStatement(sqlBeta);

                        xer.setString(1, _title);       xer.setString(2, usernameX);
                        xer.addBatch(); xer.executeBatch();
                        c.commit();     xer.close();
                        
                        System.out.println("FILM has been DELETED from Wishlist!!!");
                    }
                    else if (addWFilm == false) {System.out.println("Specified film was not in Wishlist!!");}

                    System.out.println("Film ADDED to " + usernameX + "'s Watchlist!!");
                }
                else if (addFilm == false) {System.out.println("Film is ALREADY ADDED to Watchlist!!");}
            }
            else if (selectX == 3) {
                System.out.println("----------------------------------DELETE FROM WATCHLIST----------------------------------");
                System.out.println("DELETE WHICH FILM FROM watchedlist??");
                String filmA = uX.next();

                String sqlAlpha = "DELETE FROM watched WHERE x_title = ? AND x_username = ?";
                PreparedStatement wewX = c.prepareStatement(sqlAlpha);

                wewX.setString(1, filmA);       wewX.setString(2, usernameX);        
                wewX.addBatch();                wewX.executeBatch();
                c.commit();                     wewX.close();

                System.out.println("Film DELETED from " + usernameX + "'s Watchlist!!");
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            }
            catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
        //xL.close();
    }
    
    private void wishList(String usernameW) {
        //Scanner wL = new Scanner(System.in);
        System.out.println("-------------------------------------WISHLIST MENU---------------------------------------");
        String watchLMenu[] = {"VIEW WISHLIST (1)", "ADD (2)", "DELETE (3)"};
        for (int x = 0; x < watchLMenu.length; x++) {System.out.print(watchLMenu[x] + '\t');}
        System.out.print('\n');
        int selectW = 0;
        try {
            boolean addFilm = false;
            selectW = uX.nextInt();

            if (selectW == 1) {
                String wBorder1 = "------------------------------------------------------YOUR WISHLIST------------------" +
                "---------------------------------------";
                System.out.println(wBorder1);
                String sql = "SELECT * FROM wishlist WHERE w_username = '" + usernameW + "'";
                PreparedStatement stmtW = c.prepareStatement(sql);
                ResultSet rs = stmtW.executeQuery();

                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", 
                "username", "title", "year", "genre", "score", "producer", "studios", "country", "description");

                System.out.println("-------------------------------------------------------------------------------------------------------------");
                while(rs.next()) {
                    String w1, w2, w3, w4, w5, w6, w7, w8, w9;

                    w1 = rs.getString(1);   w2 = rs.getString(2);   w3 = rs.getString(3);   w4 = rs.getString(4);
                    w5 = rs.getString(5);   w6 = rs.getString(6);   w7 = rs.getString(7);   w8 = rs.getString(8);
                    w9 = rs.getString(9);

                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", 
                    w1, w2, w3, w4, w5, w6, w7, w8, w9);
                }
                rs.close();     stmtW.close();
            }
            else if (selectW == 2) {
                System.out.println("--------------------------------------ADD FILM TO WISHLIST-------------------------------------");
                System.out.println("ADD FILM:");
                String film = uX.next();
                String sql = "SELECT w_title FROM wishlist WHERE w_username = '" + usernameW + "'";
                PreparedStatement stmtA = c.prepareStatement(sql);
                ResultSet rs = stmtA.executeQuery();

                while(rs.next()) {

                    String matchF = rs.getString(1);
                    int ctF = 0;
                    for (int x = 0; x < matchF.length(); x++) {
                        if (matchF.length() == film.length()) {
                            if (matchF.charAt(x) == film.charAt(x)) {ctF++;}
                        }
                        else {break;}
                    }
                    if (matchF.length() == ctF) {addFilm = false; break;}
                    else {addFilm = true;}
                }
                rs.close();     stmtA.close();

                if (addFilm == true) {
                    String _title, _genre, _producer, _studios, _country, _desc;
                    int _year = 0;      float _score;

                    _title = "A";   _genre = "A";      _producer = "A";   _studios = "A";   _desc = "A";    
                    _country = "A";    _score = 0;   

                    String sqlX = "SELECT * FROM movies WHERE m_title = '" + film + "'";
                    PreparedStatement stmtB = c.prepareStatement(sqlX);
                    ResultSet rsB = stmtB.executeQuery();

                    while(rsB.next()) {
                        _title = rsB.getString(1);      _year = rsB.getInt(2);          _genre = rsB.getString(3);
                        _score = rsB.getFloat(4);       _producer = rsB.getString(5);   _studios = rsB.getString(6);
                        _country = rsB.getString(8);       _desc = rsB.getString(9);
                    }
                    rsB.close();    stmtB.close();

                    String sqlZ = "INSERT INTO wishlist VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmtD = c.prepareStatement(sqlZ);
                    
                    stmtD.setString(1, usernameW);       stmtD.setString(2, _title);     stmtD.setInt(3, _year);
                    stmtD.setString(4, _genre);         stmtD.setFloat(5, _score);      stmtD.setString(6, _producer);
                    stmtD.setString(7, _studios);      stmtD.setString(8, _country);   stmtD.setString(9, _desc);
                    stmtD.addBatch();

                    stmtD.executeBatch();       c.commit();         
                    stmtD.close();
                    System.out.println("Film ADDED to " + usernameW + "'s Wishlist!!");
                }
                else if (addFilm == false) {System.out.println("Film is ALREADY PRESENT in your Wishlist!!");}
            }
            else if (selectW == 3) {
                System.out.println("----------------------------------DELETE FROM WISHLIST-------------------------------");
                System.out.println("DELETE WHICH FILM FROM wishlist??");
                String filmA = uX.next();

                String sqlAlpha = "DELETE FROM wishlist WHERE w_title = ? AND w_username = ?";
                PreparedStatement wewX = c.prepareStatement(sqlAlpha);

                wewX.setString(1, filmA);       wewX.setString(2, usernameW);       
                wewX.addBatch();    wewX.executeBatch();
                c.commit();         wewX.close();
                System.out.println("Film DELETED from " + usernameW + "'s Wishlist!!");
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            }
            catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
        //wL.close(); 
    }
    
    //NEEDS EDITING
    private void scoreList(String usernameS) {

        //Scanner sl = new Scanner(System.in);
        System.out.println("----------------------------------SCORE MENU------------------------------------");
        String scoreMenu[] = {"VIEW SCORES (1)", "UPDATE/ADD YOUR SCORE (2)"};
        try {   
            for (int h = 0; h < scoreMenu.length; h++) {System.out.print(scoreMenu[h] + '\t');}
            System.out.print('\n');
            int selN = uX.nextInt();
            if (selN == 1) {
                String sBorder1 = "-----------------------------------------------------SCORES LIST-----------------" +
                "----------------------------------------------------";
                System.out.println(sBorder1);
                String sql = "SELECT * FROM score";
                PreparedStatement stmtS = c.prepareStatement(sql);
                ResultSet rs = stmtS.executeQuery();
                
                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "username", "title", "year", "genre", "score", "studios",
                "yourscore", "rtscore", "imdbscore");
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                while(rs.next()) {
                    String s1, s2, s3, s4, s5, s6, s7, s8, s9;

                    s1 = rs.getString(1);   s2 = rs.getString(2);   s3 = rs.getString(3);   s4 = rs.getString(4);
                    s5 = rs.getString(5);   s6 = rs.getString(6);   s7 = rs.getString(7);   s8 = rs.getString(8);
                    s9 = rs.getString(9);

                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", s1, s2, s3, s4, s5, s6, s7, s8, s9);
                }
                rs.close();     stmtS.close();
            }
            else if (selN == 2) {
                System.out.println("-----------------------------------UPDATE/ADD SCORES------------------------------------");
               
                System.out.println("Please enter film you want to critique (0.0 to 10.0)");
                String filmS = uX.next();
                System.out.println("Score???: 0.0 = WORST ||| 10.0 = BEST");
                float customS = uX.nextFloat();

                String sqlS = "SELECT * FROM score";
                PreparedStatement stmtS2 = c.prepareStatement(sqlS);
                ResultSet rA = stmtS2.executeQuery();
                boolean matchA, matchB;     int ctS1, ctS2;    matchA = false;     matchB = false;
                ctS1 = 0;       ctS2 = 0;

                String s1User,s2title,s3,s4,s5,s6,s7,s8,s9;
                s1User = "A";   s2title = "B";  s3 = "C";   s4 = "D";   s5 = "E";   s6 = "F";   
                s7 = "G";   s8 = "H";   s9 = "I";
                String tempA, tempB, tempC, tempD, tempE, tempF, tempG, tempH, tempI;
                tempA = "a";    tempB = "b";    tempC = "c";    tempD = "d";    tempE = "e";    tempF = "f";
                tempG = "g";    tempH = "h";    tempI = "i";
                while (rA.next()) {
                    ctS1 = 0;   ctS2 = 0;
                    s1User = rA.getString(1);       s2title = rA.getString(2);      s3 = rA.getString(3);
                    s4 = rA.getString(4);           s5 = rA.getString(5);           s6 = rA.getString(6);
                    s7 = rA.getString(7);           s8 = rA.getString(8);           s9 = rA.getString(9);

                    for (int i = 0; i < s1User.length(); i++) {
                        if (s1User.length() == usernameS.length()) {
                            if (s1User.charAt(i) == usernameS.charAt(i)) {ctS1++;}
                        }
                        else {break;}
                    }

                    for (int j = 0; j < s2title.length(); j++) {
                        if (s2title.length() == filmS.length()) {
                            if (s2title.charAt(j) == filmS.charAt(j)) {ctS2++;}
                        }
                        else {break;}
                    }

                    if (s1User.length() == ctS1) {matchA = true;}
                    else {matchA = false;}

                    if (s2title.length() == ctS2) {matchB = true;}
                    else {matchB = false;}

                    if (matchA == true && matchB == true) {break;}
                    else if (matchA == false && matchB == true) {
                        tempA = s1User;  tempB = s2title;    tempC = s3;     tempD = s4;     tempE = s5;
                        tempF = s6;      tempG = s7;         tempH = s8;     tempI = s9;
                    }
                }
                rA.close();     stmtS2.close();

                //System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",s1User,s2title,s3,s4,s5,s6,s7,s8,s9);
                //System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",tempA,tempB,tempC,tempD,tempE,tempF,tempG,tempH,tempI);

                //System.out.println("matchA = " + matchA + " matchB = " + matchB);
                //System.out.println("TempB = " + tempB);

                if (matchA == true && matchB == true) {
                    Statement rB = c.createStatement();
                    String sql = "UPDATE score SET s_yourscore = " + customS + " WHERE s_title = '" + filmS + 
                    "' AND s_username = '" + usernameS + "'";
                    rB.execute(sql);     c.commit();     rB.close();

                    Statement rC = c.createStatement();
                    String sql2 = "UPDATE watched SET x_yourscore = " + customS + " WHERE x_title = '" + filmS +
                    "' AND x_username = '" + usernameS + "'";
                    rC.execute(sql2);     c.commit();     rC.close();

                    System.out.println("Score of specified film has been UPDATED!!!");
                }
                else {
                    String sqlbb = "INSERT INTO score VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmtSN = c.prepareStatement(sqlbb);

                    String wewlad = Float.toString(customS);
                    
                    stmtSN.setString(1, usernameS);  stmtSN.setString(2, tempB);    stmtSN.setString(3, tempC);
                    stmtSN.setString(4, tempD);      stmtSN.setString(5, tempE);    stmtSN.setString(6, tempF);
                    stmtSN.setString(7, wewlad);     stmtSN.setString(8, tempH);    stmtSN.setString(9, s9);
                    
                    stmtSN.addBatch();               stmtSN.executeBatch();           c.commit();
                    stmtSN.close();

                    System.out.println("New custom score tuple has been ADDED!!!");
                }
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            }
            catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
        //sl.close();
    }
    
    private void actorList(String usernameA) {

        //Scanner aL = new Scanner(System.in);
        String borderA = "-------------------------------------------------------------------ACTOR LIST---------------------" +
        "-------------------------------------------------------------";
        System.out.println(borderA);
        String actorMenu [] = {
            "ALL (1)", "ACTOR/ACTRESS (2)", "FILMS (3)", "BIRTH YEAR (4)", "DEATH YEAR (5)", 
            "BIRTH LOCATION (6)", "DEATH LOCATION (7)"
        };
        try {
            for (int i = 0; i < actorMenu.length; i++) {System.out.print(actorMenu[i] + '\t');}
            System.out.print('\n');
            int selA = uX.nextInt();
            String sql = "A";

            if (selA == 1) {sql = "SELECT * FROM actors";}
            else if (selA == 2) {
                System.out.println("Enter Actor/Actress: ");
                String _actor = uX.next();
                sql = "SELECT * FROM actors WHERE a_actors = '" + _actor + "'";
            }
            else if (selA == 3) {
                System.out.println("Enter keywords: ");
                String _film = uX.next();
                sql = "SELECT * FROM actors WHERE a_title like '%" + _film + "%'";
            }
            else if (selA == 4) {
                System.out.println("Enter birthyear: ");
                String _birthyear = uX.next();
                sql = "SELECT * FROM actors WHERE a_birthdate like '%" + _birthyear + "%'";
            }
            else if (selA == 5) {
                System.out.println("Enter deathyear: ");
                String _deathyear = uX.next();
                sql = "SELECT * FROM actors WHERE a_deathdate like '%" + _deathyear + "%'";
            }
            else if (selA == 6) {
                System.out.println("Enter keyword: ");
                String _birthloc = uX.next();
                sql = "SELECT * FROM actors WHERE a_birthloc like '%" + _birthloc + "%'";
            }
            else if (selA == 7) {
                System.out.println("Enter keyword: ");
                String _deathloc = uX.next();
                sql = "SELECT * FROM actors WHERE a_deathloc like '%" + _deathloc + "%'";
            }
            PreparedStatement stmtA = c.prepareStatement(sql);
            ResultSet rs = stmtA.executeQuery();

            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", "actor", "title", "birthdate", "birthloc", "deathdate", "deathloc");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

            while(rs.next()) {
                String s1 = rs.getString(1);    String s2 = rs.getString(2);    String s3 = rs.getString(3);
                String s4 = rs.getString(4);    String s5 = rs.getString(5);    String s6 = rs.getString(6);

                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", s1, s2, s3, s4, s5, s6);
            }

        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            }
            catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
        //aL.close();
    }

    private void rules () {
        String notice = "Please just keep in mind that if you decide to have an input\n" +
        "requiring two words or more, please separate the words with an underscore '_'\n" +
        "instead of a ' ' character. For example: if you want to input something like\n" +
        "'Joe Biden', you should write it out and enter as a input in this fashion:\n" +
        "'Joe_Biden'.\n" +
        "Otherwise, your input will not be registered correctly. So please, just keep\n" +
        "this in mind when navigating through the movie database options.";

        System.out.println("----------------------------------------RULES----------------------------------------");
        System.out.println(notice);
    }
    
    public static void main (String args[]) {

        phase3bperez35 entersus = new phase3bperez35();
        entersus.openConnection("data-proj-test-F1.sqlite");

        Scanner x1 = new Scanner(System.in);
        int select = 100;

        String frontMenu[] = {"MOVIES (1)", "PURCHASES (2)", "WATCHLIST (3)", 
        "WISHLIST (4)", "SCORELIST (5)", "ACTORS (6)", "USERACC (7)", "RULES (8)"};

        String username = entersus.userAcc("0");

        if(username != "NULL") {
            System.out.println("DO YOU WISH TO PROCEED!!?: Type TRUE or FALSE");
            boolean proceed = x1.nextBoolean();

            if(proceed == true) {
                while (select != 0) {
                    String menu = "----------------------------------------------MENU: (select 0 to EXIT)" +
                    "-----------------------------------------------------";
                    System.out.println(menu);
                    for(int b = 0; b < frontMenu.length; b++) {System.out.print(frontMenu[b] + '\t');}
                    System.out.print('\n');
                    select = x1.nextInt();
                    if (select == 1) {entersus.movieDB(username);}
                    else if (select == 2) {entersus.purchaseList(username);}
                    else if (select == 3) {entersus.watchList(username);}
                    else if (select == 4) {entersus.wishList(username);}
                    else if (select == 5) {entersus.scoreList(username);}
                    else if (select == 6) {entersus.actorList(username);}
                    else if (select == 7) {username = entersus.userAcc(username);}
                    else if (select == 8) {entersus.rules();}
                }
            }
            else {System.out.println("EXIT!!!");}
        }
        x1.close();
        entersus.closeConnection();
    }
}



//CHECKLIST #1      (11/28/2020 - 1:08 PM):

/*
    FUNCTION            1st Draft:      1st Testing:    2nd Testing:    3rd Testing:   
    userAcc()           COMPLETE        YES (x)         YES (O)         YES (100%) O
    createAcct()        COMPLETE        NO              YES (O)         YES (100%) O
    movieDB()           COMPLETE        NO              YES (O)         YES (100%) O
    purchaseList()      COMPLETE        NO              YES (O)         YES (100%) O
    watchList()         COMPLETE        NO              NO (X)          YES (100%) O
    wishList()          COMPLETE        NO              NO (X)          YES (100%) O
    scoreList()         COMPLETE        NO              NO (X)          YES (100%) O
    actorList()         COMPLETE        NO              YES (O)         YES (95%) O
    rules()             N/A             n/a             YES (O)         YES (100%) O

    *O:     Functions 100%
    *O-:    Functions 100% except for some errors that don't involve code so much rather whitespace parsing ' ';

*/

//edit 2nd option function in Scores

/*
    WatchList
        - ADD seems to be working but prints out an exception DONE
        - DELETE needs work DONE
    ScoreList
        - Fix it DONE
        - Just add descriptions to allow user to know what has happened.

*/