import com.dbc.retrocards.controller.RetrospectivaController;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.dbc.retrocards.service.RetrospectivaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetrocardsApplicationTests {

	@Test
	void contextLoads() {
	}


	@InjectMocks
	private RetrospectivaService retrospectivaService;
	@InjectMocks
	private RetrospectivaController retrospectivaController;


	@InjectMocks
	private RetrospectivaRepository retrospectivaRepository;

	@BeforeEach
	public void beforeEach(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deleteEmployee() throws RegraDeNegocioException {
		retrospectivaController.delete(1);
		Mockito.verify(retrospectivaRepository, Mockito.times(1)).deleteById(1);
	}
}
