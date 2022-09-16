package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id)VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        String sql = "select * from item";
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setTitle(resultSet.getString("title"));
        item.setPrice(resultSet.getDouble("price"));
        int categoryId = resultSet.getInt("category_id");
        item.setPicUrl(resultSet.getString("pic_url"));
        int userId = resultSet.getInt("user_id");
        Category category = categoryManager.getById(categoryId);
        item.setCategory(category);
        User user = userManager.getUserById(userId);
        item.setUser(user);

        return item;
    }

    public void removeItemById(int id) {
        String sql = "delete from item where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Item item) {
        String sql = "update item set title=?,price=?,category_id=?,pic_url=?,user_id=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUser().getId());
            ps.setInt(6, item.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getLastTwentyItems() {
        String sql = "SELECT * FROM item order by id desc LIMIT 20 ";

        return getItems(sql);
    }

    public List<Item> getLastTwentyItemsByCategory(int categoryId) {
        String sql = "SELECT * FROM item where category_id=" + categoryId + " order by id desc LIMIT 20 ";
        return getItems(sql);
    }

    private List<Item> getItems(String sql) {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public List<Item> getItemsByUserId(int id) {

        String sql = "select * from item WHERE user_id = ?";

        List<Item> list = new ArrayList<>();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {

                list.add(getItemFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
