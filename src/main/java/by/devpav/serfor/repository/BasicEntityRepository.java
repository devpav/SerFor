package by.devpav.serfor.repository;

import by.devpav.serfor.domain.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicEntityRepository<T extends BasicEntity> extends JpaRepository<T, Long> {
}
