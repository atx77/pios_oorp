package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.Recension;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.repository.RecensionRepository;
import hr.tvz.diplomski.pios_oorp.service.RecensionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RecensionServiceImpl implements RecensionService {

    @Resource
    private RecensionRepository recensionRepository;

    @Override
    public Recension addNewRecensionForProduct(Product product, String text, User user) {
        Recension recension = new Recension();
        recension.setProduct(product);
        recension.setCreationDate(new Date());
        recension.setText(text);
        recension.setUser(user);
        recensionRepository.save(recension);
        return recension;
    }
}
