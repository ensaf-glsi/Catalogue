package metier.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter()
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class Client {

	private Long id;
	private String nom;
	private String email;
	private String tel;
	
	/*
	public static void main(String[] args) {
		Client c = new Client();
		c.setId(3l);
		c.setNom("clt 3");
		Client c1 = Client.builder().id(2l).nom("clt 1").build();
		Client c2 = Client.builder().id(2l).nom("clt 1").build();
		System.out.println(c1 == c2);
		System.out.println(c1.equals(c2));
		System.out.println(c1);
	}
	*/
}
