package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
@Controller
public class OrdersApiController implements OrdersApi {

    private static final Logger log = LoggerFactory.getLogger(OrdersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Order> addOrder(@ApiParam(value = "An order to add" ,required=true )  @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"storeAddr\" : {\n    \"zip\" : 0,\n    \"streetAddr\" : \"1234 56th St. SW\",\n    \"city\" : \"Seattle\",\n    \"id\" : 1,\n    \"state\" : \"WA\"\n  },\n  \"price\" : 19.99,\n  \"id\" : 1,\n  \"order\" : [ {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  }, {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  } ]\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> getOrderById(@Min(0)@ApiParam(value = "an order's unique ID",required=true, allowableValues="") @PathVariable("orderId") Integer orderId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"storeAddr\" : {\n    \"zip\" : 0,\n    \"streetAddr\" : \"1234 56th St. SW\",\n    \"city\" : \"Seattle\",\n    \"id\" : 1,\n    \"state\" : \"WA\"\n  },\n  \"price\" : 19.99,\n  \"id\" : 1,\n  \"order\" : [ {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  }, {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  } ]\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> updateOrderById(@ApiParam(value = "A JSON encoded Order object" ,required=true )  @Valid @RequestBody Order body,@Min(0)@ApiParam(value = "an order's unique ID",required=true, allowableValues="") @PathVariable("orderId") Integer orderId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"storeAddr\" : {\n    \"zip\" : 0,\n    \"streetAddr\" : \"1234 56th St. SW\",\n    \"city\" : \"Seattle\",\n    \"id\" : 1,\n    \"state\" : \"WA\"\n  },\n  \"price\" : 19.99,\n  \"id\" : 1,\n  \"order\" : [ {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  }, {\n    \"sizeDesc\" : \"Small\",\n    \"price\" : 19.99,\n    \"menusOn\" : [ {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    }, {\n      \"pizzas\" : [ null, null ],\n      \"storesOffering\" : [ {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      }, {\n        \"specials\" : [ {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        }, {\n          \"offeredAt\" : [ null, null ],\n          \"id\" : 1,\n          \"desc\" : \"50% off two large pizzas\"\n        } ],\n        \"id\" : 1\n      } ],\n      \"ingredients\" : [ null, null ],\n      \"id\" : 1\n    } ],\n    \"ingredients\" : [ {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    }, {\n      \"name\" : \"Sausage\",\n      \"isGlutenFree\" : true,\n      \"id\" : 1,\n      \"category\" : \"Meat\"\n    } ],\n    \"id\" : 1,\n    \"sizeInches\" : 11\n  } ]\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

}
