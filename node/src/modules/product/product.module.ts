import { Module } from '@nestjs/common';
import { ProductEntity } from './entities/product.entitie';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductService } from './services/product.service';
import { ProductController } from './controllers/product.controller';

@Module({
  imports: [
    TypeOrmModule.forFeature([ProductEntity]),
  ],
  providers: [ProductService],
  controllers: [ProductController],
  exports: [ProductService],
})
export class ProductModule {}