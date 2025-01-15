import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { ProductEntity } from '../entities/product.entitie';
import { Repository } from 'typeorm';
import { ProductDTO } from '../dto/ProductDTO';

@Injectable()
export class ProductService {
  constructor(
    @InjectRepository(ProductEntity)
    private readonly productRepository: Repository<ProductEntity>,
  ) {}

  async findAll(): Promise<ProductEntity[]> {
    return await this.productRepository.find();
  }

  async createProducts(products: ProductDTO[]): Promise<string> {
    try {
      for (const product of products) {
        await this.productRepository.save(product);
      }

      return "SUCCESS";
    } catch (e) {
      return e;
    }
  }
}
