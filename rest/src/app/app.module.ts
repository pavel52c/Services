import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { OrderModule } from '../order/order.module';

// eslint-disable-next-line @typescript-eslint/no-var-requires
const dbConfig = require('../../ormconfig');

@Module({
  imports: [
    TypeOrmModule.forRoot(dbConfig),
    OrderModule,
  ],
})
export class AppModule {}
