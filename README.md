# cozy-commerce-api

## Overview

- Inspired by Shopee, but reimagined to deliver a more personalized, seamless, and efficient shopping experience.

## MVP

- Create new account
- Sign in
- View products
- Add to cart
- Checkout

## Data models

### Customer

| Field           | Type    | Constraints                            |
|-----------------|---------|----------------------------------------|
| `id`            | UUID    | PRIMARY KEY, DEFAULT gen_random_uuid() |
| `email`         | STRING  | NOT NULL, UNIQUE (case-insensitive)    |
| `password_hash` | STRING  | NOT NULL                               |
| `first_name`    | STRING  | NOT NULL                               |
| `last_name`     | STRING  | NOT NULL                               |
| `created_at`    | INSTANT | NOT NULL, DEFAULT now()                |

### Address

| Field          | Type    | Constraints                                       |
|----------------|---------|---------------------------------------------------|
| `id`           | LONG    | PRIMARY KEY, AUTO-GENERATED                       |
| `customer_id`  | UUID    | NOT NULL, FK, REF: customer.id, ON DELETE CASCADE |
| `phone`        | STRING  | NOT NULL                                          |
| `address_line` | STRING  | NOT NULL                                          |
| `district`     | STRING  | NOT NULL                                          |
| `city`         | STRING  | NOT NULL                                          |
| `is_default`   | BOOLEAN | NOT NULL, DEFAULT false                           |

### Store

| Field         | Type    | Constraints                            |
|---------------|---------|----------------------------------------|
| `id`          | UUID    | PRIMARY KEY, DEFAULT gen_random_uuid() |
| `customer_id` | UUID    | NOT NULL, FK, REF: customer.id         |
| `name`        | STRING  | NOT NULL                               |
| `slug`        | STRING  | NOT NULL, UNIQUE                       |
| `description` | STRING  | NOT NULL                               |
| `created_at`  | INSTANT | NOT NULL, DEFAULT now()                |

### Product

| Field         | Type    | Constraints                            |
|---------------|---------|----------------------------------------|
| `id`          | UUID    | PRIMARY KEY, DEFAULT gen_random_uuid() |
| `store_id`    | UUID    | NOT NULL, FK, REF: store.id            |
| `name`        | STRING  | NOT NULL                               |
| `description` | STRING  | NOT NULL                               |
| `created_at`  | INSTANT | NOT NULL, DEFAULT now()                |

### Product Variant

| Field          | Type       | Constraints                                      |
|----------------|------------|--------------------------------------------------|
| `id`           | LONG       | PRIMARY KEY, AUTO-GENERATED                      |
| `product_id`   | UUID       | NOT NULL, FK, REF: product.id, ON DELETE CASCADE |
| `sku`          | STRING     | NOT NULL, UNIQUE                                 |
| `qty_in_stock` | INT        | NOT NULL, DEFAULT 0, CHECK (qty_in_stock >= 0)   |
| `price`        | BIGDECIMAL | NOT NULL, CHECK (price >= 0), NUMERIC(12,2)      |

### Category

| Field               | Type   | Constraints                                                             |
|---------------------|--------|-------------------------------------------------------------------------|
| `id`                | LONG   | PRIMARY KEY, AUTO-GENERATED                                             |
| `name`              | STRING | NOT NULL                                                                |
| `parent_id`         | LONG   | FK, REF: category.id, NULL for root categories, CHECK (parent_id <> id) |
| `(parent_id, name)` |        | UNIQUE (sibling names; roots are compared with each other)              |

### Product Category

| Field                       | Type | Constraints                                      |
|-----------------------------|------|--------------------------------------------------|
| `product_id`                | UUID | NOT NULL, FK, REF: product.id, ON DELETE CASCADE |
| `category_id`               | LONG | NOT NULL, FK, REF: category.id                   |
| `(product_id, category_id)` |      | PRIMARY KEY                                      |

### Option

| Field  | Type   | Constraints                 |
|--------|--------|-----------------------------|
| `id`   | LONG   | PRIMARY KEY, AUTO-GENERATED |
| `name` | STRING | NOT NULL, UNIQUE            |

### Option Value

| Field                | Type   | Constraints                                                         |
|----------------------|--------|---------------------------------------------------------------------|
| `id`                 | LONG   | PRIMARY KEY, AUTO-GENERATED                                         |
| `option_id`          | LONG   | NOT NULL, FK, REF: option.id                                        |
| `value`              | STRING | NOT NULL                                                            |
| `(option_id, value)` |        | UNIQUE                                                              |
| `(id, option_id)`    |        | UNIQUE (target of the composite FK in Product Variant Option Value) |

### Product Variant Option Value

| Field                             | Type | Constraints                                                                 |
|-----------------------------------|------|-----------------------------------------------------------------------------|
| `product_variant_id`              | LONG | NOT NULL, FK, REF: product_variant.id, ON DELETE CASCADE                    |
| `option_id`                       | LONG | NOT NULL                                                                    |
| `option_value_id`                 | LONG | NOT NULL                                                                    |
| `(product_variant_id, option_id)` |      | PRIMARY KEY (a variant has exactly one value per option)                    |
| `(option_value_id, option_id)`    |      | FK, REF: option_value.(id, option_id) (the value must belong to the option) |

## API Routes

**Base URL:** `/api/v1`
**Auth:** JWT in `Authorization: Bearer <token>` header

### Authentication

| Method | Route          | Description               |
|--------|----------------|---------------------------|
| POST   | `/auth/signup` | Create a customer account |
| POST   | `/auth/signin` | Returns access token      |

### Customer

| Method | Route                | Description               |
|--------|----------------------|---------------------------|
| GET    | `/customers/account` | Create a customer account |
