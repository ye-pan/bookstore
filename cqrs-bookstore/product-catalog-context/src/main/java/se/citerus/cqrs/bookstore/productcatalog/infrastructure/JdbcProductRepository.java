package se.citerus.cqrs.bookstore.productcatalog.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import se.citerus.cqrs.bookstore.productcatalog.domain.Book;
import se.citerus.cqrs.bookstore.productcatalog.domain.Product;
import se.citerus.cqrs.bookstore.productcatalog.domain.ProductRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcProductRepository implements ProductRepository  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Product> productRowMapper = (rs, rowNum)->{
        String isbn = rs.getString("isbn");
        String title = rs.getString("title");
        String description = rs.getString("description");

        Product product = new Product();
        product.setBook(new Book(isbn, title, description));
        product.setId(rs.getString("id"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setPublisherContractId(rs.getString("publisherContractId"));
        product.setCreateTime(rs.getDate("createTime"));
        product.setLastUpdateTime(rs.getDate("lastUpdateTime"));
        return product;
    };

    @Override
    public Product get(String productId) {
        Assert.notNull(productId, "请求参数错误");
        String sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, productId);
    }

    @Override
    public void save(Product product) {
        String sql = "insert into product " +
                "(id, isbn, title, description, price, publisherContractId, createTime, lastUpdateTime) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        product.setId(id);
        Date now = new Date();
        product.setCreateTime(now);
        product.setLastUpdateTime(now);
        jdbcTemplate.update(sql, product.getId(), product.getBook().getIsbn(), product.getBook().getTitle(),
                product.getBook().getDescription(), product.getPrice(), product.getPublisherContractId(),
                product.getCreateTime(), product.getLastUpdateTime());

    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

}
