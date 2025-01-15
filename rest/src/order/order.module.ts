import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { OrderEntity } from './entities/order';
import { OrderService } from './services/order.service';
import { OrderController } from './controllers/order.controller';

@Module({
  imports: [
    TypeOrmModule.forFeature([OrderEntity]),
  ],
  providers: [OrderService],
  controllers: [OrderController],
  exports: [OrderService],
})
export class OrderModule {}