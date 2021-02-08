package insertNewMonument;

public class existingCity {
    public int existCity(String code3, String cityName){
        if(code3==null || cityName == null || code3.equals("") || cityName.equals(""))
            return -1;

        String query = "SELECT id FROM city WHERE CountryCode LIKE ? AND name LIKE ? ";
        Connection con=null;
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, code3);
            ps.setString(2, cityName);
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);

            if(rs.next()){
                int id = rs.getInt("id");
                con.close();
                return id;
            }else{
                con.close();
                return -1;
            }

        }catch(Exception e){

        }finally {

        }
        return -1;
    }

    public boolean insertNewMonument( String code3, String city, String name ){
        if(name==null || name.equals(""))
            return false;

        int cityId=existCity(code3, city);
        System.out.println(cityId);
        if(cityId==-1)
            return false;
        // vlozenie pamiatky
        String query = "INSERT INTO monument(name, city) VALUES (?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,name);
            ps.setInt(2,cityId);
            ps.executeUpdate();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
