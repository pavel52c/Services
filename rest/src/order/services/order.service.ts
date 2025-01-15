import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { DeepPartial, DeleteResult, Repository } from 'typeorm';
import { OrderEntity } from '../entities/order';
import { OrderDTO } from '../dto/OrderDTO';

@Injectable()
export class OrderService {
  constructor(
    @InjectRepository(OrderEntity)
    private readonly orderRepository: Repository<OrderEntity>,
  ) {}

   async findAll(): Promise<OrderEntity[]> {
    return await this.orderRepository.find();
   }

   async findOne(id: OrderEntity['id']): Promise<OrderEntity> {
    return await this.orderRepository.findOneBy({id});
   }

   async create(order: OrderDTO): Promise<OrderEntity> {
    return await this.orderRepository.save({ ...order, products: JSON.stringify(order.products) });
   }

   async remove(id: OrderEntity['id']): Promise<DeleteResult> {
    return await this.orderRepository.delete(id)
   }
}
