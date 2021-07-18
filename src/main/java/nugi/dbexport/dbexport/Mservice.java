package nugi.dbexport.dbexport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mservice {
    @Autowired
    private Repo repo;

    public List<MerchantCustomer> findById(String merchantUuid){
        return repo.findByMerchantUuid(merchantUuid);
    }

    public List <MerchantCustomer>findAll (){
        return repo.findAll();
    }
}
