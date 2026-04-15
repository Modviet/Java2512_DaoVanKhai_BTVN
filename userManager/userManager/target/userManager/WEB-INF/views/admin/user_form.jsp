<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin.Users - User Form</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet" />

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

 <style>
    :root {
      --brand: #e50012;
      --ink: #111827;
      --muted: #6b7280;
      --bg: #f6f7fb;
      --card: #ffffff;
      --ring: rgba(229, 0, 18, .18);

      /* Bootstrap overrides */
      --bs-border-radius: 0.9rem;
      --bs-border-radius-lg: 1rem;
      --bs-border-radius-sm: 0.7rem;
      --bs-body-color: var(--ink);
      --bs-body-font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
    }

    html, body { height: 100%; }

    body {
      font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
      text-rendering: optimizeLegibility;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      line-height: 1.5;
      background:
        radial-gradient(1200px 500px at 20% -10%, rgba(229, 0, 18, 0.10), transparent 60%),
        radial-gradient(900px 500px at 90% 10%, rgba(17, 24, 39, 0.08), transparent 68%),
        var(--bg);
      color: var(--ink);
    }

    .shell { max-width: 960px; }

    .topbar {
      position: sticky;
      top: 0;
      z-index: 1020;
      background: rgba(255,255,255,.85);
      backdrop-filter: blur(10px);
      border-bottom: 1px solid rgba(17, 24, 39, 0.06);
      box-shadow: 0 10px 35px rgba(17, 24, 39, 0.06);
    }

    .brand {
      font-weight: 900;
      letter-spacing: -0.6px;
    }

    .brand .dot { color: var(--brand); }

    .card-elev {
      background: var(--card);
      border: 1px solid rgba(17, 24, 39, 0.06);
      border-radius: 16px;
      box-shadow: 0 18px 45px rgba(17, 24, 39, 0.08);
    }

    .action-btn { border-radius: 12px; font-weight: 800; }

    .btn-brand { background: var(--brand); border-color: var(--brand); }
    .btn-brand:hover { background: #c80010; border-color: #c80010; }

    .form-control, .form-select {
      border-radius: 12px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      padding: 10px 12px;
      background: rgba(255,255,255,.9);
    }

    .btn:focus, .btn:active,
    .form-control:focus, .form-select:focus {
      box-shadow: 0 0 0 .25rem var(--ring) !important;
    }

    .form-control:focus, .form-select:focus {
      border-color: rgba(229, 0, 18, 0.35);
    }

    .hint { font-size: .9rem; color: var(--muted); }

    .avatarPreview {
      width: 72px; height: 72px;
      border-radius: 18px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      background: linear-gradient(135deg, rgba(229,0,18,.20), rgba(17,24,39,.06));
      overflow: hidden;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 900;
    }

    .avatarPreview img { width: 100%; height: 100%; object-fit: cover; }

    .form-title { font-weight: 950; letter-spacing: -0.8px; }

    .section {
      border: 1px dashed rgba(17, 24, 39, 0.18);
      border-radius: 16px;
      padding: 16px;
      background: rgba(255,255,255,.6);
    }

    .error-text { color: #b42318; font-weight: 700; font-size: .9rem; }

    @media (max-width: 576px) {
      .form-title { font-size: 1.35rem; }
      .topbar .brand { font-size: 1.15rem !important; }
    }
  </style>
</head>

<body>

<div class="topbar">
  <div class="container shell py-3 d-flex justify-content-between align-items-center">

    <div class="brand fs-4">
      Admin<span class="dot">.</span>Users
    </div>

    <div class="d-flex gap-2">
      <a class="btn btn-outline-dark action-btn"
         href="${pageContext.request.contextPath}/admin/users">
        <i class="bi bi-arrow-left me-1"></i> Quay lại
      </a>
    </div>

  </div>
</div>

<div class="container shell my-4">

  <div class="card-elev p-4">

    <!-- TITLE -->
    <div class="h3 fw-bold mb-1">
      <c:choose>
        <c:when test="${mode == 'edit'}">Sửa người dùng</c:when>
        <c:otherwise>Tạo người dùng</c:otherwise>
      </c:choose>
    </div>

    <div class="hint mb-4">
      Spring MVC + JSTL + Jakarta
    </div>

    <!-- FORM -->
    <form method="post"
          action="<c:choose>
                    <c:when test='${mode == "edit"}'>
                      ${pageContext.request.contextPath}/admin/users/${user.id}/edit
                    </c:when>
                    <c:otherwise>
                      ${pageContext.request.contextPath}/admin/users/create
                    </c:otherwise>
                  </c:choose>">

      <!-- FULL NAME -->
      <div class="mb-3">
        <label class="form-label fw-semibold">Họ tên</label>
        <input type="text"
               class="form-control"
               name="fullName"
               value="${user.fullName}" />
      </div>

      <!-- EMAIL -->
      <div class="mb-3">
        <label class="form-label fw-semibold">Email</label>
        <input type="email"
               class="form-control"
               name="email"
               value="${user.email}" />
      </div>

      <!-- GENDER -->
      <div class="mb-3">
        <label class="form-label fw-semibold">Giới tính</label>
        <select class="form-select" name="gender">
          <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
          <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
          <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
        </select>
      </div>

      <!-- ROLE -->
      <div class="mb-3">
        <label class="form-label fw-semibold">Role</label>
        <select class="form-select" name="role">
          <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>USER</option>
          <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
        </select>
      </div>

      <!-- PASSWORD -->
      <c:if test="${mode == 'create'}">
        <div class="mb-3">
          <label class="form-label fw-semibold">Password</label>
          <input type="password" class="form-control" name="password"/>
        </div>
      </c:if>

      <!-- ERRORS -->
      <c:if test="${not empty errors}">
        <div class="alert alert-danger">
          <c:forEach var="e" items="${errors}">
            <div>${e.value}</div>
          </c:forEach>
        </div>
      </c:if>

      <!-- BUTTON -->
      <div class="d-flex gap-2 justify-content-end">
        <a href="${pageContext.request.contextPath}/admin/users"
           class="btn btn-outline-dark action-btn">
          Huỷ
        </a>

        <button class="btn btn-brand text-white action-btn">
          <i class="bi bi-check2-circle me-1"></i> Lưu
        </button>
      </div>

    </form>

  </div>
</div>
</body>
</html>