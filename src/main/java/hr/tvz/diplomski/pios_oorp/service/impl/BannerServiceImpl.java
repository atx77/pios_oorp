package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Banner;
import hr.tvz.diplomski.pios_oorp.repository.BannerRepository;
import hr.tvz.diplomski.pios_oorp.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
}
