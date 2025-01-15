import { Body, Controller, Delete, Get, Param, Post, Put } from '@nestjs/common';
import { OrderService } from '../services/order.service';
import { OrderEntity } from '../entities/order';
import { OrderDTO } from '../dto/OrderDTO';
import { DeleteResult } from 'typeorm';

@Controller('orders')
export class OrderController {
  constructor(
    private readonly orderService: OrderService,
  ) {}

  @Get()
  async findAll(): Promise<OrderEntity[]> {
    return await this.orderService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: OrderEntity['id']): Promise<OrderEntity> {
    return this.orderService.findOne(id);
  }

  @Post()
  async create(@Body() order: OrderDTO): Promise<OrderEntity> {
    return await this.orderService.create(order);
  }

  @Put()
  async update(@Body() order: OrderDTO): Promise<OrderEntity> {
    const response =await this.orderService.create(order);
    console.log({response});
    return response;
  }

  @Delete(':id')
  remove(@Param('id') id: OrderEntity['id']): Promise<DeleteResult> {
    return this.orderService.remove(id);
  }
}
