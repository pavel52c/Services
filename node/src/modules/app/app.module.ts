import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductModule } from '../product/product.module';

// eslint-disable-next-line @typescript-eslint/no-var-requires
const dbConfig = require('../../../ormconfig');

@Module({
  imports: [
    TypeOrmModule.forRoot(dbConfig),
    ProductModule,
  ],
})
export class AppModule {}
