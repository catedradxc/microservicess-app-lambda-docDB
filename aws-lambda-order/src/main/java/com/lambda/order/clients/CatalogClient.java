package com.lambda.order.clients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.order.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class CatalogClient {

    private final Logger log = LoggerFactory.getLogger(CatalogClient.class);

    public static class ItemPagedResources extends PagedModel<Item> {

    }

    private RestTemplate restTemplate;
    private String catalogServiceHost;
    private long catalogServicePort;

    @Autowired
    public CatalogClient() {
        super();
        this.restTemplate = getRestTemplate();
        this.catalogServiceHost = catalogServiceHost;
        this.catalogServicePort = catalogServicePort;
    }

    protected RestTemplate getRestTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
        converter.setObjectMapper(mapper);

        return new RestTemplate(Collections.<HttpMessageConverter<?>>singletonList(converter));
    }

    public double price(String itemId) {
        return getOne(itemId).getPrice();
    }

    public Collection<Item> findAll() {
        PagedModel<Item> pagedResources = restTemplate.getForObject(catalogURL(), ItemPagedResources.class);
        return pagedResources.getContent();
    }

    private String catalogURL() {
        String url = "https://wby27cgbrc.execute-api.eu-central-1.amazonaws.com/dev/catalog";
        log.trace("Catalog: URL {} ", url);
        return url;
    }

    public Item getOne(String itemId) {
        return restTemplate.getForObject(catalogURL() + itemId, Item.class);
    }
}
