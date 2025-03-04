import { NestFactory } from '@nestjs/core';
import { AppModule } from './modules/app/app.module';

async function start() {
  const PORT = process.env.PORT || 5000;
  const app = await NestFactory.create(AppModule);
  app.enableCors();


  await app
      .listen(PORT)
      .then(() => console.log(`Server started on port = ${PORT}`));
}

start();