package hr.mperhoc.iisproject.auth.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtils {
	private static final byte[] SECRET = Base64.getDecoder().decode("mtM+9YhdDNaxWyvLly9P4u/+sCPWOHcUjUY8HoSpdyg1");
	private static final String ALGORITHM = "HmacSHA256";

	public static String generateJwt(String username) {
		Instant now = Instant.now();

		return Jwts.builder().subject(username).audience().add("IISProject-API").and().issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(30, ChronoUnit.MINUTES))).signWith(Keys.hmacShaKeyFor(SECRET)).compact();
	}

	public static void parseJwt(String token) {
		SecretKey key = new SecretKeySpec(SECRET, ALGORITHM);
		Jws<Claims> claims = Jwts.parser().decryptWith(key).build().parseSignedClaims(token);
	}
}
