package team.spring.trip.like.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {

	private int courseNum;
	private int userNum;
    private Date clickDate;
}
