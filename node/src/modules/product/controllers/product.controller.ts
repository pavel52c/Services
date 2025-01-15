import { Body, Controller, Get, Post } from '@nestjs/common';
import { ProductService } from '../services/product.service';
import { ProductEntity } from '../entities/product.entitie';
import { ProductDTO } from '../dto/ProductDTO';

@Controller('products')
export class ProductController {
  constructor(
    private readonly productService: ProductService,
  ) {}

  @Get()
  async findAll(): Promise<ProductEntity[]> {
    return await this.productService.findAll();
  }

  @Post('createList')
  async createFromList(@Body() products: ProductDTO[]): Promise<String> {
    return await this.productService.createProducts(products);
  }
}
