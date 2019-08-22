package com.neuedu.service.impl;

import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.CartMapper;
import com.neuedu.pojo.Cart;

import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.vo.CartProductVo;
import com.neuedu.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public ServerResponse getCartList(Integer id) {
        List<Cart> list=cartMapper.selectCartByUserId(id);


        CartVo cartVo=new CartVo();
        cartVo.setAllChecked(false);
        BigDecimal totalPrice=new BigDecimal(0);
        int checkedCount=0;
        List<CartProductVo> cartProductVoList=new ArrayList<>();
        for(Cart cart:list){
            CartProductVo cartProductVo=new CartProductVo();
            Product product=cart.getProduct();
            cartProductVo.setId(cart.getId());
            cartProductVo.setUserId(cart.getUserId());
            cartProductVo.setProductId(product.getId());
            cartProductVo.setQuantity(cart.getQuantity());
            cartProductVo.setProductName(product.getName());
            cartProductVo.setProductSubtitle(product.getSubtitle());
            cartProductVo.setProductMainImage(product.getMainImage());
            cartProductVo.setProductPrice(product.getPrice());
            cartProductVo.setProductStatus(product.getStatus());
            cartProductVo.setProductStock(product.getStock());
            cartProductVo.setProductChecked(cart.getChecked());
            if(cartProductVo.getQuantity()>cartProductVo.getProductStock()){
                cartProductVo.setLimitQuantity("LIMIT_NUM_FAIL");
            }else {
                cartProductVo.setLimitQuantity("LIMIT_NUM_SUCCESS");
            }
            //如果商品被选中就更新购物车总价格
            if(cartProductVo.getProductChecked()==1){
                checkedCount++;
                totalPrice= totalPrice.add(cartProductVo.getProductPrice().multiply(BigDecimal.valueOf(cartProductVo.getQuantity())));

            }
            cartProductVo.setProductTotalPrice(cartProductVo.getProductPrice().multiply(BigDecimal.valueOf(cartProductVo.getQuantity())));
            cartProductVoList.add(cartProductVo);



        }
        //如果都被选中，设置all checked属性

        if(checkedCount==cartProductVoList.size()){
            cartVo.setAllChecked(true);
        }
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setCartTotalPrice(totalPrice);

        if(cartProductVoList.size()==0){
            return ServerResponse.createServerResponseByFail(1,"购物车空空如也~");
        }

        return ServerResponse.createServerResponseBySuccess(null,cartVo);

    }

    @Override
    public ServerResponse addProduct(Integer productId, Integer count,Integer id) {

        if(productId==null||count==null){
            return ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }

        if( cartMapper.insert(productId,count,id)==0){
            return  ServerResponse.createServerResponseByFail(2,"更新数据失败");
        }

        return this.getCartList(id);

    }

    @Override
    public ServerResponse updateProductCount(Integer productId, Integer count, Integer id) {
        if(productId==null||count==null){
            return ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }

        if( cartMapper.update(productId,count,id)==0){
            return  ServerResponse.createServerResponseByFail(2,"更新数据失败");
        }

        return this.getCartList(id);
    }

    @Override
    public ServerResponse deleteCartProducts(Integer[] productsId, Integer id) {
        if(productsId.length==0){
            return  ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }
        for(Integer productId:productsId){
            if(cartMapper.existProductById(productId,id)==0){
                return ServerResponse.createServerResponseByFail(3,"商品不存在");

            }
        }
        cartMapper.deleteCartProductById(productsId,id);

        return this.getCartList(id);


    }

    @Override
    public ServerResponse selectCartProduct(Integer productId, Integer id) {
        if(productId==null){
            return  ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }
        if(cartMapper.existProductById(productId,id)==0){
            return ServerResponse.createServerResponseByFail(3,"商品不存在");
        }

        cartMapper.updateCheckedByProductId(productId,id);

        return this.getCartList(id);
    }

    @Override
    public ServerResponse unselectCartProduct(Integer productId, Integer id) {
        if(productId==null){
            return  ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }
        if(cartMapper.existProductById(productId,id)==0){
            return ServerResponse.createServerResponseByFail(3,"商品不存在");
        }

        cartMapper.updateUncheckedByProductId(productId,id);

        return this.getCartList(id);
    }

    @Override
    public ServerResponse getCartProductCount(Integer id) {

        int count=cartMapper.cartProductCount(id);
        return ServerResponse.createServerResponseBySuccess(null,count);

    }

    @Override
    public ServerResponse selectCartProductAll(Integer id) {
        cartMapper.updateCheckedAllByProductId(id);
        return this.getCartList(id);
    }

    @Override
    public ServerResponse unselectCartProductAll(Integer id) {
        cartMapper.updateUncheckedAllByProductId(id);
        return this.getCartList(id);
    }


}
