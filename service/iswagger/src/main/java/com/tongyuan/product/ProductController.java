package com.tongyuan.product;

import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zhangcy on 2018/6/1
 */
@RestController
public class ProductController {
    @ApiOperation("创建产品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品请求对象",paramType = "body",dataType = "Product",name="product",required = true)
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "成功"),
            @ApiResponse(code=600,message = "无效的产品请求对象")
    })
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        if(product == null){
            return ResponseEntity.status(600).build();
        }
        //插入数据库
        return ResponseEntity.ok(product);
    }

    @ApiOperation("根据产品ID删除产品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id",paramType = "path",dataType = "long",name="id",required = true)
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "成功"),
            @ApiResponse(code=600,message = "无效的产品id")
    })
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id")long id){
        if(id<=0){
            return ResponseEntity.status(600).build();
        }
        //从数据库删除，返回product
        Product product=new Product();
        return ResponseEntity.ok(product);
    }

    @ApiOperation("根据产品id更新产品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id",paramType = "path",dataType = "long",name="id",required = true),
            @ApiImplicitParam(value = "产品相关属性",paramType = "body",dataType = "Map<String,Object>",name="fieldMap",required = true)
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "成功"),
            @ApiResponse(code=600,message = "无效的产品id")
    })
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id")long id,@RequestBody Map<String,Object> fieldMap){
        if(id<=0){
            return ResponseEntity.status(600).build();
        }
        //从数据库更新，返回product
        Product product=new Product();
        return ResponseEntity.ok(product);
    }

    @ApiOperation("根据产品id查询产品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id",dataType = "long",paramType = "path",name="id",required = true)
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "成功"),
            @ApiResponse(code=600,message = "无效的产品id")
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")long id){
        if(id<=0){
            return ResponseEntity.status(600).build();
        }
        //从数据库根据id查询，返回product
        Product product=new Product();
        return ResponseEntity.ok(product);
    }
}
