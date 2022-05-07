from django.urls import include, path

from rest_framework import routers

from .views import JsonItemViewSet
from .views import XMLItemViewSet

router = routers.DefaultRouter()
router.register(r'json/items', JsonItemViewSet)
router.register(r'xml/items', XMLItemViewSet)

urlpatterns = [
   path('', include(router.urls)),
]