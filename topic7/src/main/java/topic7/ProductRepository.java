package topic7;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    public List<Product> findByCategory(String category);
    public List<Product> findByPriceLessThan(int price);
}
