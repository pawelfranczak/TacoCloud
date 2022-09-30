package tacos.data;

import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfor(taco);
		taco.setId(tacoId);
		taco.getIngredients().forEach(i -> saveIngredientToTaco(i, tacoId));
		return null;
	}
	
	private long saveTacoInfor(Taco taco) {
		taco.setCreateAt(new Date());
		
		PreparedStatementCreatorFactory preparedStatementCreatorFactory = 
				new PreparedStatementCreatorFactory(
						"INSERT INTO Taco(name, createdAt) values (?, ?)",
						Types.VARCHAR, Types.TIMESTAMP);
		
		preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator preparedStatementCreator = 
				preparedStatementCreatorFactory.newPreparedStatementCreator(
						Arrays.asList(taco.getName(), new Timestamp(taco.getCreateAt().getTime())));
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(preparedStatementCreator, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
	private void saveIngredientToTaco(String ingredient, long tacoId) {
		jdbcTemplate.update("INSERT INTO Taco_Ingredients (taco, ingredient) values (?, ?)",
							tacoId, ingredient);
	}

}
