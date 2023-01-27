package phoenix.networkmanager;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import phoenix.dto.ProductOrderRequestDto;
import phoenix.dto.ProductResponseDto;
import phoenix.util.JsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
@Slf4j
@Service
public class CallProductServiceImpl implements CallProductService {
	public List<ProductResponseDto> callProductService(ProductOrderRequestDto productOrderRequestDto, String url) {


		Map<String, Object> response = postJsonUsingHttpClient(productOrderRequestDto, url);

		if (response.containsKey("result")) {
			List<Map<String, Object>> responseMap = (List) response.get("result");

			return responseMap.stream().map(map -> {
				return ProductResponseDto.builder()
						.productId(Long.valueOf(String.valueOf(map.get("productId"))))
						.productName((String) map.get("productName"))
						.productSKU((String) map.get("productSKU"))
						.productDescription((String) map.get("productDescription"))
						.productPrice((Double) map.get("productPrice"))
						.qty((Integer) map.get("qty")).build();
			}).collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}

	}


	private Map<String, Object> postJsonUsingHttpClient(ProductOrderRequestDto productOrderRequestDto, String url) {

		try {
			final HttpPost httpPost = new HttpPost(url);

			final String json = JsonUtil.getJsonString(productOrderRequestDto, ProductOrderRequestDto.class);
			final StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			try (CloseableHttpClient client = HttpClients.createDefault();
			     CloseableHttpResponse httpResponse = (CloseableHttpResponse) client.execute(httpPost)) {

				log.info("POST Response Status:: " + httpResponse.getStatusLine().getStatusCode());

				BufferedReader reader = new BufferedReader(new InputStreamReader(
						httpResponse.getEntity().getContent()));

				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
				reader.close();

				// print result
				log.info(response.toString());


				client.close();
				return JsonUtil.getJsonToObject(response.toString());

			}

		} catch (Exception e) {

		}

		return Collections.emptyMap();
	}

}
